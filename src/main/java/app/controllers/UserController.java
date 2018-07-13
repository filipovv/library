package app.controllers;

import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;
import app.services.AuthorisationService;
import app.services.UserService;

public class UserController {
    private UserRepository userRepository;
    private UserService userService;
    private AuthorisationService authorisationService;

    public UserController(UserRepository userRepository, UserService userService, AuthorisationService authorisationService) {
        this.userRepository = userRepository;
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
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getHistoryByUser(user);
    }

    public String getUserInfo(String sessionId) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getUserInfo(user);
    }
}
