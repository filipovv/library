package app.controllers;

import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;
import app.services.AuthorisationService;
import app.services.ProfileService;

public class UserController {
    private UserRepository userRepository;
    private ProfileService profileService;
    private AuthorisationService authorisationService;

    public UserController(UserRepository userRepository, ProfileService profileService, AuthorisationService authorisationService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.authorisationService = authorisationService;
    }

    public void registerUser(User user) {
        this.authorisationService.registerUser(user);
    }

    public void login(Credentials credentials) {
        this.authorisationService.login(credentials);
    }

    public String getUserHistory(String sessionId, User user) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        return this.profileService.getHistoryByUser(user);
    }

    public String getUserInfo(String sessionId, User user) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        return this.profileService.getUserInfo(user);
    }
}
