package app.controllers;

import app.entities.user.Credentials;
import app.entities.user.User;
import app.services.AuthorisationService;
import app.services.UserService;

/**
 * UserController class serves as a controller for all operations regarding users.
 */
public class UserController {
    private UserService userService;
    private AuthorisationService authorisationService;

    /**
     * Constructor for the User Controller
     *
     * @param userService          The service for the users
     * @param authorisationService The authorisation service
     */
    public UserController(UserService userService, AuthorisationService authorisationService) {
        this.userService = userService;
        this.authorisationService = authorisationService;
    }

    /**
     * Method used to logout a currently logged in user.
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     */
    public void logout(String sessionId) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        this.authorisationService.deleteCurrentSession();
    }

    /**
     * Method used to register a user into the application.
     *
     * @param user Object of type User to be registered
     */
    public void registerUser(User user) {
        this.authorisationService.registerUser(user);
    }

    /**
     * Method used to authorise a user with given credentials and generate a session for him
     *
     * @param credentials Object of type Credentials to be verified
     * @return String value representing the generated session id
     */
    public String login(Credentials credentials) {
        return this.authorisationService.login(credentials);
    }

    /**
     * Method used to get the history for the current user
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @return String value representing the summed up history for the currently logged in user
     */
    public String getUserHistory(String sessionId) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getHistoryByUser(user);
    }

    /**
     * Method used to get the information about the current user
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @return String value representing the summed up information about the user.
     */
    public String getUserInfo(String sessionId) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.userService.getUserInfo(user);
    }
}
