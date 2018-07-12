package app.controllers;

import app.Session;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;
import app.services.ProfileService;

public class UserController {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private UserRepository userRepository;
    private ProfileService profileService;
    private Session session;

    // TODO: ADD VALIDATION FOR SESSION ID;

    public UserController(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    public void registerUser(User user) {
        if (this.userRepository.isAlreadyRegistered(user.getCredentials())) {
            throw new IllegalArgumentException("User with that username already registered.");
        }

        this.userRepository.addUser(user);
    }

    public void login(Credentials credentials) {
        User user = this.userRepository.findUserByCredentials(credentials);
        String sessionId = this.generateSessionId();
        Session session = new Session(sessionId, user);
        this.session = session;
    }

    public String getUserHistory(User user) {
        return this.profileService.getHistoryByUser(user);
    }

    public String getUserInfo(User user) {
        return this.profileService.getUserInfo(user);
    }

    private String generateSessionId() {
        StringBuilder builder = new StringBuilder();
        int count = ALPHA_NUMERIC_STRING.length();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }

        return builder.toString();
    }

    public Session getSession() {
        return this.session;
    }
}
