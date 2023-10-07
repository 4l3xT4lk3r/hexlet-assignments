package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import java.util.Collections;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    /*
        принимать данные формы
        устанавливать специальный токен в куке
        Пользователь должен сохраняться в репозитории
        должен происходить редирект на /users/{id}
     */
    public static void create(Context ctx) {
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");
        String token = Security.generateToken();
        ctx.cookie("auth-token", token);
        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        ctx.redirect("/users/" + user.getId());
    }

    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id).get();
        if (user.getToken().equals(ctx.cookie("auth-token"))) {
            ctx.render("users/show.jte", Collections.singletonMap("user", user));
        }else {
         ctx.redirect("users/new");
        }
    }

    // END
}
