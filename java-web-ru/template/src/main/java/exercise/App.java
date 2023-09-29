package exercise;

import exercise.dto.users.UserPage;
import io.javalin.Javalin;

import java.util.List;

import exercise.model.User;
import exercise.dto.users.UsersPage;
import io.javalin.http.NotFoundResponse;

import java.util.Collections;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            UsersPage usersPage = new UsersPage(USERS);
            ctx.render("users/index.jte", Collections.singletonMap("users", usersPage));
        });
        app.get("/users/{id}", ctx -> {
            int id = ctx.pathParamAsClass("id", Integer.class).get();
            User user = USERS.stream()
                    .filter(m -> m.getId() == id)
                    .findFirst().orElseThrow(() -> new NotFoundResponse("User not found"));
            ctx.render("users/show.jte", Collections.singletonMap("user", new UserPage(user)));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
