package app.entities.book;

import java.time.LocalDate;

/**
 * Class Author provides information and properties for objects of type Author
 */
public class Author {
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    /**
     * Constructor for the Author class
     *
     * @param name        The name of the author
     * @param country     The country the author is from
     * @param dateOfBirth The date of birth of the author
     * @param dateOfDeath The date of the authors death
     */
    public Author(String name, String country, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        this.setName(name);
        this.setCountry(country);
        this.setDateOfBirth(dateOfBirth);
        this.setDateOfDeath(dateOfDeath);
    }

    /**
     * Constructor for the Author class
     *
     * @param name        The name of the author
     * @param country     The country the author is from
     * @param dateOfBirth The date of birth of the author
     */
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

    private void setCountry(String country) {
        if ("".equals(country) || country == null) {
            throw new IllegalArgumentException("Country of Author cannot be set to null or empty.");
        }
        this.country = country;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth of the author cannot be null.");
        }
        this.dateOfBirth = dateOfBirth;
    }

    private void setDateOfDeath(LocalDate dateOfDeath) {
        if (dateOfDeath == null) {
            throw new IllegalArgumentException("Date of death of the author cannot be null.");
        }
        this.dateOfDeath = dateOfDeath;
    }
}
