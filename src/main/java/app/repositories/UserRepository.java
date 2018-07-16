package app.repositories;

import app.entities.user.Credentials;
import app.entities.user.User;

import java.util.HashSet;
import java.util.Set;

public class UserRepository {
    private Set<User> users;

    public UserRepository() {
        this.users = new HashSet<>();
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add a null user to repository.");
        } else if (this.users.contains(user)) {
            throw new IllegalArgumentException("User already exists in the repository.");
        }

        this.users.add(user);
    }

    public void removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        } else if (!this.users.contains(user)) {
            throw new IllegalArgumentException("User does not exist in the repository.");
        }

        this.users.remove(user);
    }

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

        if (currentUser == null) {
            throw new IllegalArgumentException("User with provided credentials does not exist.");
        }

        return currentUser;
    }

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
