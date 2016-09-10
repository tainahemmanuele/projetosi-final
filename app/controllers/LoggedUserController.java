package controllers;

import exceptions.AlreadyExistingContentException;
import exceptions.InputException;
import models.Archive;
import models.Content;
import models.Directory;
import models.Usuario;
import play.data.FormFactory;
import play.mvc.*;
import util.FormularioConteudo;
import util.FormularioEdicaoConta;
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

    public Result editarConta() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        FormularioEdicaoConta formularioEdicao = formFactory.form(FormularioEdicaoConta.class).bindFromRequest().get();
        if (loggedUser.getSenha().equals(formularioEdicao.getSenhaAtual())) {
            if (formularioEdicao.getSenhaNova1().equals(formularioEdicao.getSenhaNova2())) {
                try {
                    loggedUser.setUsername(formularioEdicao.getUsername());
                    loggedUser.setEmail(formularioEdicao.getEmail());
                    session("email", formularioEdicao.getEmail());
                    loggedUser.setSenha(formularioEdicao.getSenhaNova1());
                } catch (InputException e) {
                    flash("erroEdit", e.getMessage());
                }
            } else
                flash("erroEdit", "As senhas não correspondem.");
        } else
            flash("erroEdit", "Senha incorreta.");
        return redirect(routes.LoggedUserController.index());
    }

    public Result criarArquivoRender() {
        return ok(texto.render());
    }

    public Result criarArquivoTexto() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Directory directory = (Directory) loggedUser.getContent(session().get("dir"));
        FormularioConteudo formularioConteudo = formFactory.form(FormularioConteudo.class).bindFromRequest().get();
        try {
            Archive arquivo = new Archive(formularioConteudo.getName(), formularioConteudo.getType());
            directory.addContent(arquivo);
        } catch (Exception e) {
            flash("erroNew", e.getMessage());
        }
        return redirect(routes.LoggedUserController.openDirectory(session().get("dir")));
    }

    public Result newDirRender() {
        String path = session("dir");
        return ok(newDirectory.render(path));
    }

    public Result newDirectory() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Directory directory = (Directory) loggedUser.getContent(session().get("dir"));
        FormularioConteudo formularioConteudo = formFactory.form(FormularioConteudo.class).bindFromRequest().get();
        try {
            Directory newDirectory = new Directory(formularioConteudo.getName());
            directory.addContent(newDirectory);
        } catch (Exception e) {
            flash("erroNew", e.getMessage());
        }
        return redirect(routes.LoggedUserController.openDirectory(session().get("dir")));
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
        return ok(viewTexto.render(archive));
    }

    public Result verArquivo(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Archive archive = (Archive) loggedUser.getContent(path);
        return ok(viewTexto.render(archive));
    }

    public Result excluirArquivo(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Archive archive = (Archive) loggedUser.getContent(path);
        Directory folder = archive.getParent();
        folder.delContent(archive);
        return redirect(routes.LoggedUserController.openDirectory(folder.getPath()));
    }

    public Result compartilharArquivo(String emailUser, String path, String tipo) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Usuario sharingUser = Application.getUsuarioEmail(emailUser);
        try {
            loggedUser.compartilhar(sharingUser, tipo, path);
        } catch (AlreadyExistingContentException e) {
            flash("repeticao","Ja existe um arquivo com esse nome sendo compartilhado.");
        }
        return ok();
    }

}
