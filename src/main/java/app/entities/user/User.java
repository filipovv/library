package app.entities.user;

import app.entities.enums.Gender;

import java.util.Objects;

/**
 * User class defines the properties of an object of type User
 */
public class User {
    private Credentials credentials;
    private String name;
    private int age;
    private Gender gender;
    private String email;
    private Address address;
    private boolean agreedGdpr;

    /**
     * Constructor for the User class
     *
     * @param credentials The credentials of the user
     * @param name        The name of the user
     * @param age         The age of the user
     * @param gender      The gender of the user
     * @param email       The email address of the user
     * @param address     The Address of the user
     * @param agreedGdpr  Does the user agree to the GDPR
     */
    public User(Credentials credentials, String name, int age, Gender gender, String email, Address address, boolean agreedGdpr) {
        this.setCredentials(credentials);
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setEmail(email);
        this.setAddress(address);
        this.setAgreedGdpr(agreedGdpr);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User user = (User) obj;
        boolean check = this.getCredentials().getUsername().equals(user.getCredentials().getUsername()) && this.getEmail().equals(user.getEmail());
        return check;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCredentials().getUsername(), this.getEmail());
    }

    @Override
    public String toString() {
        return String.format("-- User Info --%nName: %s%nAge: %d%nGender: %s%nE-mail: %s%n",
                this.getName(), this.getAge(), this.getGender(), this.getEmail());
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    private void setCredentials(Credentials credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials of user cannot be null.");
        }
        this.credentials = credentials;
    }

    private String getName() {
        return this.name;
    }

    private void setName(String name) {
        if ("".equals(name) || name == null) {
            throw new IllegalArgumentException("Name of user cannot be set to null or empty.");
        }
        this.name = name;
    }

    private int getAge() {
        return this.age;
    }

    private void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age of user cannot be set to 0 or lower.");
        }
        this.age = age;
    }

    private Gender getGender() {
        return this.gender;
    }

    private void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender of user cannot be null.");
        }
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        if ("".equals(email) || email == null) {
            throw new IllegalArgumentException("Email of user cannot be set to null or empty.");
        }
        this.email = email;
    }

    private void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address of user cannot be null.");
        }
        this.address = address;
    }

    public boolean isAgreedGdpr() {
        return this.agreedGdpr;
    }

    private void setAgreedGdpr(boolean agreedGdpr) {
        if (!agreedGdpr) {
            throw new IllegalArgumentException("User must agree to GDPR to use the library.");
        }
        this.agreedGdpr = true;
    }
}
