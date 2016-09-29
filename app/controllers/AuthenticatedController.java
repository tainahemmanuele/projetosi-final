package controllers;

import play.mvc.Result;
/**
 * Created by Tainah Emmanuele on 29/09/2016.
 */
public class AuthenticatedController extends SecuredController {

    public static Result authenticatedAction() {
        return ok("Hi, " + request().username());
    }
}