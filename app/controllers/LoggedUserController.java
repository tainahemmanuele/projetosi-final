package controllers;

import models.Archive;
import models.Content;
import models.Directory;
import models.Usuario;
import play.data.FormFactory;
import play.mvc.*;
import views.html.*;

import javax.inject.Inject;

/**
 * Created by SauloSamuel on 29/07/2016.
 */
public class LoggedUserController extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result index() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        return openDirectory(loggedUser.getFolder().getPath());
    }

    public Result openDirectory(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Content content = loggedUser.getContent(path);
        if(content.isDirectory()) {
            session().remove("dir");
            session().put("dir", content.getPath());
            return ok(usuario.render(loggedUser));
        } else
            return badRequest("ERRO");
    }


    public Result logout() {
        session().clear();
        return redirect(routes.Application.loginRender());
    }

    public Result editarRender(){
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        return ok(editar.render(loggedUser));
    }

    public Result editarConta()throws Exception{
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Usuario usuarioNovo = formFactory.form(Usuario.class).bindFromRequest().get();
        loggedUser.setUsername(usuarioNovo.getUsername());
        loggedUser.setEmail(usuarioNovo.getEmail());
        loggedUser.setSenha(usuarioNovo.getSenha());
        return redirect(routes.LoggedUserController.index());
    }

    public Result criarArquivoRender() {
        return ok(texto.render());
    }

    public Result criarArquivoTexto() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Archive arquivo = formFactory.form(Archive.class).bindFromRequest().get();
            loggedUser.adicionaArquivo(arquivo);
        return redirect(routes.LoggedUserController.index());
    }

    public Result newDirRender() {
        String path = session("dir");
        return ok(newDirectory.render(path));
    }

    public Result newDirectory(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Directory directory = (Directory) loggedUser.getContent(path);
        Directory newDirectory = formFactory.form(Directory.class).bindFromRequest().get();
        newDirectory.setParent(directory);
        directory.addContent(newDirectory);
        return redirect(routes.LoggedUserController.openDirectory(directory.getPath()));
    }

    public Result editarArquivoRender(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Archive archive = (Archive) loggedUser.getContent(path);
        return ok(editarTexto.render(archive));
    }

    public Result editarArquivoTexto(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Archive novoArquivo = formFactory.form(Archive.class).bindFromRequest().get();
        Archive archive = (Archive) loggedUser.getContent(path);
        archive.setTexto(novoArquivo.getText());
        archive.setName(novoArquivo.getName());
        return redirect(routes.LoggedUserController.index());
    }

}
