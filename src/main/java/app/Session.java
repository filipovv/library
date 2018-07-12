package app;

import app.entities.user.User;

public class Session {
    private String id;
    private User user;

    public Session(String id, User user) {
        this.setId(id);
        this.setUser(user);
    }

    public String getId() {
        return this.id;
    }

    private void setId(String id) {
        if ("".equals(id) || id == null) {
            throw new IllegalArgumentException("Session id cannot be set to null or be empty.");
        }
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    private void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User in session cannot be null.");
        }
        this.user = user;
    }
}
