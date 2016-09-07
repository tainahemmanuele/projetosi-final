package util;

import play.mvc.*;
import models.*;
import play.*;

import static play.mvc.Controller.flash;

/**
 * Created by Tainah Emmanuele on 07/09/2016.
 */
public class Secured extends Security.Authenticator {

    public Secured() {
    }

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("login");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        flash("erro","Você não está logado no sistema");
        return redirect(controllers.routes.Application.login());
    }


}
