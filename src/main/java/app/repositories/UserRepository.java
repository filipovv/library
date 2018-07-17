package app.repositories;

import app.entities.user.Credentials;
import app.entities.user.User;

import java.util.HashSet;
import java.util.Set;

/**
 * UserRepository class serves as a repository for objects of type User in the application
 */
public class UserRepository {
    private Set<User> users;

    /**
     * Constructor for the UserRepository
     */
    public UserRepository() {
        this.users = new HashSet<>();
    }

    /**
     * Method used to add a user to the repository
     *
     * @param user Object of type User to be added to the user repository
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add a null user to repository.");
        } else if (this.users.contains(user)) {
            throw new IllegalArgumentException("User already exists in the repository.");
        }

        this.users.add(user);
    }

    /**
     * Method used to remove a given user from the repository.
     *
     * @param user Object of the User to be removed from the user repositoty.
     */
    void removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        } else if (!this.users.contains(user)) {
            throw new IllegalArgumentException("User does not exist in the repository.");
        }

        this.users.remove(user);
    }

    /**
     * Method used to find a certain user by his/her credentials.
     *
     * @param credentials Object of type Credentials to be used as search value
     * @return Object of type User representing the user matching the credentials provided.
     * Returns null if no user found with this credentials.
     */
    public User findUserByCredentials(Credentials credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials parameter cannot be null to complete operation.");
        }
        User currentUser = null;

        for (User user : users) {
            if (user.getCredentials().verify(credentials)) {
                currentUser = user;
            }
        }

        return currentUser;
    }

    /**
     * Method used to determine if a given user is already registered in the application.
     *
     * @param user Object of type User to be used as search value.
     * @return Boolean value representing if the user already exists in the application or not.
     */
    public boolean isAlreadyRegistered(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User parameter cannot be null to complete operation.");
        }
        boolean isRegistered = false;
        for (User entry : this.users) {
            if (entry.getCredentials().equals(user.getCredentials()) || entry.getEmail().equals(user.getEmail())) {
                isRegistered = true;
                break;
            }
        }
        return isRegistered;
    }

    public Set<User> getUsers() {
        return this.users;
    }
}
