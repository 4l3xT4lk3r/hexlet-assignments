package exercise.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import io.javalin.apibuilder.CrudHandler;
import io.ebean.DB;

import java.util.List;
import java.util.Objects;

import exercise.domain.User;
import exercise.domain.query.QUser;

import io.javalin.validation.BodyValidator;
import io.javalin.validation.JavalinValidation;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.lang3.StringUtils;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                .id.asc()
                .findList();
        String json = DB.json().toJson(users);
        ctx.json(json);
        // END
    }

    ;

    public void getOne(Context ctx, String id) {
        // BEGIN
        User user = new QUser().id.equalTo(Integer.parseInt(id)).findOne();
        String json = DB.json().toJson(user);
        ctx.json(json);
        // END
    }

    ;

    public void create(Context ctx) {
        // BEGIN
        String body = ctx.body();
        User user = ctx.bodyValidator(User.class)
                .check(u -> !u.getFirstName().isEmpty(), "Имя не должно быть пустым")
                .check(u -> !u.getLastName().isEmpty(), "Фамилия не должна быть пустой")
                .check(u -> !u.getEmail().isEmpty(), "Email не должна быть пустым")
                .check(u -> u.getEmail().matches("^(.+)@(\\S+)$"), "Email должен быть валидным")
                .check(u -> !u.getPassword().isEmpty(), "Пароль не должно быть пустым")
                .check(u -> u.getPassword().length() > 4, "Пароль должен быть больше четырех символов")
                .check(u -> StringUtils.isNumeric(u.getPassword()), "Пароль должен содержать только цифры")
                .get();
        user.save();
        // END
    }

    ;

    public void update(Context ctx, String id) {
        // BEGIN
        String body = ctx.body();
        User user = DB.json().toBean(User.class, body);
        user.setId(id);
        user.update();
        // END
    }

    ;

    public void delete(Context ctx, String id) {
        // BEGIN
        new QUser().id.equalTo(Integer.parseInt(id)).delete();
        // END
    }

    ;
}
