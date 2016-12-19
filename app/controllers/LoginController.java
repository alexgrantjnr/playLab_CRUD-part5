package controllers;

import controllers.*;
import controllers.routes;
import models.users.Login;
import models.users.User;
import play.*;
import play.Environment;
import play.mvc.Controller;

/**
 * Created by Gaming I5 PC on 19/12/2016.
 */
import play.api.*;
import play.mvc.*;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.*;
import java.util.List;
import javax.inject.Inject;

import views.html.*;

public class LoginController extends Controller {
    private FormFactory formFactory;
    private Environment env;

    @Inject
    public LoginController(Environment e, FormFactory f) {

        this.env = e;
        this.formFactory = f;
    }

    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class);

        return ok(login.render(loginForm, User.getUserById(session().get("email"))));
    }

    public Result loginSubmit() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm,  User.getUserById(session().get("email"))));
        }
        else {
            session().clear();

            session("email", loginForm.get().getEmail());
        }
        return redirect(controllers.routes.LoginController.login());
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been loged out");
        return redirect(routes.LoginController.login());
    }
}
