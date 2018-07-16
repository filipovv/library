package app.entities.enums;

/**
 * Enumeration class for genders that can be used by the application
 */
public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }
}
