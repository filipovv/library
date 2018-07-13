package app.services;

import app.Session;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;

public class AuthorisationService {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private UserRepository userRepository;
    private Session session;

    public AuthorisationService(UserRepository userRepository, Session session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    public void deleteCurrentSession() {
        this.session = null;
    }

    public boolean validateSession(String id) {
        if (this.session == null) {
            throw new IllegalArgumentException("Current session has expired or user logged out.");
        }

        boolean flag = true;

        if ("".equals(id) || id == null) {
            flag = false;
        }

        if (!this.getSession().getId().equals(id)) {
            flag = false;
        }

        return flag;
    }

    public void registerUser(User user) {
        if (this.userRepository.isAlreadyRegistered(user)) {
            throw new IllegalArgumentException("User with that username or email already registered.");
        }

        if (!user.isAgreedGdpr()) {
            throw new IllegalArgumentException("User must agree to GDPR to be able to register.");
        }
        this.userRepository.addUser(user);
    }

    public String login(Credentials credentials) {
        User user = this.userRepository.findUserByCredentials(credentials);
        String sessionId = this.generateSessionId();
        Session session = new Session(sessionId, user);
        this.session = session;
        return session.getId();
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
