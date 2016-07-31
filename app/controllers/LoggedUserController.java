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
    static Usuario loggedUser;

    public Result index() {
        return ok(usuario.render(loggedUser));
    }

    public Result logout() {
        loggedUser = null;
        return redirect(routes.Application.loginRender());
    }

    public Result editarRender(){
        return ok(editar.render(loggedUser));
    }

    public Result editarConta()throws Exception{
        Usuario usuarioNovo = formFactory.form(Usuario.class).bindFromRequest().get();
        loggedUser.setUsername(usuarioNovo.getUsername());
        loggedUser.setEmail(usuarioNovo.getEmail());
        loggedUser.setSenha(usuarioNovo.getSenha());
        return redirect(routes.LoggedUserController.index());
    }
}
