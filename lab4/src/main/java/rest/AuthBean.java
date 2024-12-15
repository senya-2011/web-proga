package rest;

import db.DataBase;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import logic.LoggerService;
import logic.User;

import java.util.ArrayList;
import java.util.Map;

@Stateless
@Path("/auth")
public class AuthBean {

    @Inject
    DataBase dataBase;

    @Inject
    LoggerService loggerService;

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(User user) {
        String login = user.getLogin();
        String password = user.getPassword();

        loggerService.logWarning(login + " : " + password);

        ArrayList<User> users = dataBase.getUsers();

        User existingUser = users.stream()
                .filter(u -> u.getLogin().equals(login))
                .findFirst()
                .orElse(null);



        if (existingUser != null) {
            loggerService.logInfo(existingUser.toString());
            if (existingUser.getPassword().equals(password)) {
                return Response.status(Response.Status.OK)
                        .entity("Успешно вошли")
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Ошибка входа")
                        .build();
            }
        } else {
            dataBase.createUser(user);
            return Response.status(Response.Status.OK)
                    .entity("Успешно создан пользователь")
                    .build();
        }
    }
}
