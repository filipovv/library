package app.entities.book;

import java.time.LocalDate;

public class Author {
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public Author(String name, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.setName(name);
        this.setCountry(country);
        this.setDateOfBirth(dateOfBirth);
        this.setDateOfDeath(dateOfDeath);
    }

    public Author(String name, String country, LocalDate dateOfBirth) {
        this.setName(name);
        this.setCountry(country);
        this.setDateOfBirth(dateOfBirth);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if ("".equals(name) || name == null) {
            throw new IllegalArgumentException("Name of Author cannot be set to null or empty.");
        }
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    private void setCountry(String country) {
        if ("".equals(country) || country == null) {
            throw new IllegalArgumentException("Country of Author cannot be set to null or empty.");
        }
        this.country = country;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth of the author cannot be null.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return this.dateOfDeath;
    }

    private void setDateOfDeath(LocalDate dateOfDeath) {
        if (dateOfDeath == null) {
            throw new IllegalArgumentException("Date of death of the author cannot be null.");
        }
        this.dateOfDeath = dateOfDeath;
    }
}
