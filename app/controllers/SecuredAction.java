package controllers;

import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Results;
import java.lang.Throwable;
import java.util.concurrent.CompletionStage;

import models.Usuario;
/**
 * Created by  Tainah Emmanuele on 29/09/2016.
 */
/*public class SecuredAction extends Action.Simple {

   public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        String token = getTokenFromHeader(ctx);
        if (token != null) {
            Usuario user = Usuario.find.where().eq("token", token).findUnique();
            if (user != null) {
                ctx.request().setUsername(user.getUsername());
                return delegate.call(ctx);
            }
        }
        Result unauthorized = Results.unauthorized("unauthorized");
        return F.Promise.pure(unauthorized);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }
}*/
