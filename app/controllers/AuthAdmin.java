package controllers;

import models.users.User;
import play.mvc.*;
import java.util.concurrent.CompletionStage;
import  java.util.concurrent.CompletableFuture;
/**
 * Created by Gaming I5 PC on 19/12/2016.
 */
public class AuthAdmin extends Action.Simple {

    public CompletionStage<Result> call(Http.Context ctx) {
        String id = ctx.session().get("email");
        if (id != null) {
            User u = User.getUserById(id);
            if ("admin".equals(u.getRole())) {
                return delegate.call(ctx);
            }
        }
        return CompletableFuture(redirect(routes.LoginController.login()));
    }

}
