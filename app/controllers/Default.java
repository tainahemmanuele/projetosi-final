package controllers;

import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
/**
 * Created by administrador1 on 30/07/2016.
 */
public class Default extends Controller{
    private FormFactory formFactory;

    public Result index() {
        return ok();
    }

}
