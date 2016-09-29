package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import models.Usuario;
/**
 * Created by Tainah Emmanuele on 29/09/2016.
 */

public class ActionAuthentication extends Security.Authenticator{

    @Override
    public String getUsername(Http.Context ctx) {
        String token = getTokenFromHeader(ctx);
        if (token != null) {
            Usuario user = Usuario.find.where().eq("token", token).findUnique();
            if (user != null) {
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

    private String getTokenFromHeader(Http.Context ctx) {
        String[] authTokenHeaderValues = ctx.request().headers().get("X-AUTH-TOKEN");
        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
            return authTokenHeaderValues[0];
        }
        return null;
    }

}
