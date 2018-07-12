package app.entities.user;

import app.entities.enums.Gender;

public class User {
    private Credentials credentials;
    private String name;
    private int age;
    private Gender gender;
    private String email;
    private Address address;
    private boolean agreedGdpr;

    public User(Credentials credentials, String name, int age, Gender gender, String email, Address address, boolean agreedGdpr) {
        this.setCredentials(credentials);
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setEmail(email);
        this.setAddress(address);
        this.setAgreedGdpr(agreedGdpr);
    }

    public Credentials getCredentials() {
        return credentials;
    }

    private void setCredentials(Credentials credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials of user cannot be null.");
        }
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if ("".equals(name) || name == null) {
            throw new IllegalArgumentException("Name of user cannot be set to null or empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Age of user cannot be set to 0 or lower.");
        }
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    private void setGender(Gender gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender of user cannot be null.");
        }
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        if ("".equals(email) || email == null) {
            throw new IllegalArgumentException("Email of user cannot be set to null or empty.");
        }
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    private void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Address of user cannot be null.");
        }
        this.address = address;
    }

    public boolean isAgreedGdpr() {
        return agreedGdpr;
    }

    private void setAgreedGdpr(boolean agreedGdpr) {
        if (!agreedGdpr) {
            throw new IllegalArgumentException("User must agree to GDPR to use the library.");
        }
        this.agreedGdpr = true;
    }
}
