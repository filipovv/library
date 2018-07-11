package app.entities.book;

import java.time.LocalDate;

public class Author {
    private String name;
    private String country;
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    private Author(AuthorBuilder builder) {
        this.name = builder.name;
        this.country = builder.country;
        this.dateOfBirth = builder.dateOfBirth;
        this.dateOfDeath = builder.dateOfDeath;
    }

    public static class AuthorBuilder {

        private String name;
        private String country;
        private LocalDate dateOfBirth;
        private LocalDate dateOfDeath;

        public AuthorBuilder(String name, String country, LocalDate dateOfBirth) {
            this.setName(name);
            this.setCountry(country);
            this.setDateOfBirth(dateOfBirth);
        }

        public AuthorBuilder setDateOfDeath(LocalDate dateOfDeath) {
            this.dateOfDeath = dateOfDeath;
            return this;
        }

        public Author build() {
            return new Author(this);
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

        public String getName() {
            return this.name;
        }

        public String getCountry() {
            return this.country;
        }

        public LocalDate getDateOfBirth() {
            return this.dateOfBirth;
        }

        private void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public LocalDate getDateOfDeath() {
            return this.dateOfDeath;
        }
    }
}
