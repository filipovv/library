package app.services;

import app.entities.Session;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;

/**
 * AuthorisationService class is used to create users, verify credentials and authorise access to the application.
 */
public class AuthorisationService {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private UserRepository userRepository;
    private Session session;

    /**
     * Constructor for the AuthorisationService
     *
     * @param userRepository The repository for the users
     */
    public AuthorisationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method used to remove the current session.
     */
    public void deleteCurrentSession() {
        this.session = null;
    }

    /**
     * Method used to make a verification of the provided id and the sessionId of the current session.
     *
     * @param id String value to be matched with the one currently stored in the session.
     * @return Boolean value representing if the ids match or not
     */
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

    /**
     * Method used to register a user into the application.
     *
     * @param user Object of type User to be registered.
     */
    public void registerUser(User user) {
        if (this.userRepository.isAlreadyRegistered(user)) {
            throw new IllegalArgumentException("User with that username or email already registered.");
        }

        if (!user.isAgreedGdpr()) {
            throw new IllegalArgumentException("User must agree to GDPR to be able to register.");
        }
        this.userRepository.addUser(user);
    }

    /**
     * Method used to grand access to the application functionality via given credentials.
     *
     * @param credentials Object of type Credentials to be used as login credentials
     * @return String value representing the generated session id the used must have
     * to authorise him/herself for further use of the application.
     */
    public String login(Credentials credentials) {
        User user = this.userRepository.findUserByCredentials(credentials);
        if (user == null) {
            throw new IllegalArgumentException("User with provided credentials does not exist.");
        }

        String sessionId = this.generateSessionId();
        Session session = new Session(sessionId, user);
        this.session = session;
        return session.getId();
    }

    /**
     * Method used to generate an alpha-numeric string serving as a unique id for the session.
     *
     * @return String value representing the unique session id string
     */
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
