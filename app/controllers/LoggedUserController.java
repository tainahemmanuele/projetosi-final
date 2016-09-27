package controllers;

import exceptions.AlreadyExistingContentException;
import exceptions.InputException;
import models.*;
import play.data.FormFactory;
import play.mvc.*;
import util.FormularioCompartilhamento;
import util.FormularioConteudo;
import util.FormularioEdicaoConta;
import views.html.*;

import javax.inject.Inject;
import java.util.List;

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

    public Result logout() {
        session().clear();
        return redirect(routes.Application.loginRender());
    }

    public Result editarRender(){
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        return ok(editar.render(loggedUser));
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


  // ----------- EDICAO DE DIRETORIOS -------------

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



    // -------- EDICAO DE ARQUIVOS ---------

    public Result criarArquivoRender() {
        return ok(texto.render());
    }

    public Result criarArquivoTexto() {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        Directory directory = (Directory) loggedUser.getContent(session().get("dir"));
        FormularioConteudo formularioConteudo = formFactory.form(FormularioConteudo.class).bindFromRequest().get();
        try {
            IArchive arquivo = new Archive(formularioConteudo.getName(), formularioConteudo.getType());
            directory.addContent(arquivo);
            arquivo.setOwner(loggedUser);
        } catch (Exception e) {
            flash("erroNew", e.getMessage());
        }
        return redirect(routes.LoggedUserController.openDirectory(session().get("dir")));
    }

    public Result editarArquivoRender(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        return ok(editarTexto.render(archive));
    }

    public Result editarArquivoTexto(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive novoArquivo = formFactory.form(Archive.class).bindFromRequest().get();
        IArchive archive = (IArchive) loggedUser.getContent(path);
        archive.setTexto(novoArquivo.getText());
        archive.setName(novoArquivo.getName());
        return ok(viewTexto.render(archive, loggedUser));
    }

    public Result verArquivo(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        return ok(viewTexto.render(archive, loggedUser));
    }

    public Result excluirArquivo(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        Directory folder = archive.getParent();

        if (archive.isShared()) {
            loggedUser.cancelarCompartilhamento(path);
        }

        loggedUser.removeArchive(path);
        return redirect(routes.LoggedUserController.openDirectory(folder.getPath()));
    }

    // ---------------  COMPARTILHAMENTO -------------------

    public Result compartilharRender(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        return ok(newSharing.render(archive));
    }

    public Result compartilharArquivo(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        FormularioCompartilhamento formularioComp = formFactory.form(FormularioCompartilhamento.class).bindFromRequest().get();
        if (Application.getUsuarioEmail(formularioComp.getEmailUser()) != null) {
            Usuario sharingUser = Application.getUsuarioEmail(formularioComp.getEmailUser());
            try {
                loggedUser.compartilhar(sharingUser, formularioComp.getTipo(), path);
            } catch (AlreadyExistingContentException e) {
                flash("repeticao","Ja existe um arquivo com esse nome sendo compartilhado.");
            }
        }
        return verArquivo(path);
    }

    public Result cancelarCompartilhamento(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        if (archive.getOwner().equals(loggedUser)) {
            loggedUser.cancelarCompartilhamento(path);
        } else {
            loggedUser.sairCompartilhamento(path);
        }
        return redirect(routes.LoggedUserController.openDirectory(archive.getParent().getPath()));
    }

    public Result removeCompRender(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        IArchive archive = (IArchive) loggedUser.getContent(path);
        return ok(removeSharing.render(archive));
    }

    public Result removeUsuarioCompartilhado(String path) {
        Usuario loggedUser = Application.getUsuarioEmail(session("email"));
        FormularioCompartilhamento formularioComp = formFactory.form(FormularioCompartilhamento.class).bindFromRequest().get();
        if (Application.getUsuarioEmail(formularioComp.getEmailUser()) != null) {
            Usuario sharingUser = Application.getUsuarioEmail(formularioComp.getEmailUser());
            IArchive archive = (IArchive) loggedUser.getContent(path);
            String sharingPath = sharingUser.DEFAULT_FOLDER_NAME + "/" + sharingUser.SHARING_FOLDER_NAME + "/" + archive.getNameType();
            sharingUser.sairCompartilhamento(sharingPath);
        }
        return verArquivo(path);
    }

}
