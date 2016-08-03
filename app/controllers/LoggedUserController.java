package controllers;

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
        return ok(usuario.render(loggedUser));
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
}
