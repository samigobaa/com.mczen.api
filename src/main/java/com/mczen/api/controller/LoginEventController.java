package com.mczen.api.controller;

import com.mczen.api.model.LoginHistory;
import com.mczen.api.service.LoginEventService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login-events")
public class LoginEventController {

    private final LoginEventService loginEventService;

    public LoginEventController() {
        loginEventService = new LoginEventService();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LoginHistory getAll() {
        return this.loginEventService.getAll();
    }
}
