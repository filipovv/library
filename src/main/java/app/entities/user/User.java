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
        this.credentials = credentials;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    private void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public boolean isAgreedGdpr() {
        return agreedGdpr;
    }

    private void setAgreedGdpr(boolean agreedGdpr) {
        this.agreedGdpr = agreedGdpr;
    }
}
