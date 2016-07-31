package controllers;

import models.Usuario;
import play.mvc.*;
import views.html.*;

/**
 * Created by SauloSamuel on 29/07/2016.
 */
public class LoggedUserController extends Controller {

    static Usuario loggedUser;

    public Result index() {
        return TODO;
    }

    public Result logout() {
        loggedUser = null;
        return redirect(routes.Application.index());
    }
}
