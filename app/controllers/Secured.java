package controllers;

import controllers.*;
import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;
import models.users.*;
/**
 * Created by Gaming I5 PC on 19/12/2016.
 */
public class Secured extends Security.Authenticator{

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(controllers.routes.LoginController.login());
    }
}
