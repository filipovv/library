package app.controllers;

import app.entities.user.Credentials;
import app.entities.user.User;
import app.services.AuthorisationService;
import app.services.UserService;

public class UserController {
    private UserService userService;
    private AuthorisationService authorisationService;

    public UserController(UserService userService, AuthorisationService authorisationService) {
        this.userService = userService;
        this.authorisationService = authorisationService;
    }

    public void registerUser(User user) {
        this.authorisationService.registerUser(user);
    }

    public String login(Credentials credentials) {
        return this.authorisationService.login(credentials);
    }

    public String getUserHistory(String sessionId) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getHistoryByUser(user);
    }

    public String getUserInfo(String sessionId) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getUserInfo(user);
    }
}
