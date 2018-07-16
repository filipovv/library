package app.entities.user;

import java.util.Objects;

/**
 * Credentials class defines the properties of authorisation credentials of a user
 */
public class Credentials {
    private String username;
    private String password;

    /**
     * Constructor for the credentials of the user
     *
     * @param username The username of the user
     * @param password The password of the user
     */
    public Credentials(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * Method used to verify that the credentials match as the ones provided
     *
     * @param credentials Object of type Credentials to be verified
     * @return Boolean value representing if the credentials match
     */
    public boolean verify(Credentials credentials) {
        return this.username.equals(credentials.getUsername()) && this.password.equals(credentials.getPassword());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Credentials)) {
            return false;
        }

        Credentials credentials = (Credentials) obj;
        boolean check = this.getUsername().equalsIgnoreCase(credentials.getUsername());
        return check;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getUsername());
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if ("".equals(username) || username == null) {
            throw new IllegalArgumentException("Username in credentials cannot be set to null or empty.");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        if ("".equals(password) || password == null) {
            throw new IllegalArgumentException("Password in credentials cannot be set to null or empty.");
        }
        this.password = password;
    }
}
