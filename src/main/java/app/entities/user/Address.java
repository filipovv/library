package app.entities.user;

public class Address {
    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {
        this.setStreet(street);
        this.setCity(city);
        this.setCountry(country);
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        this.country = country;
    }
}
