package app.entities.user;

/**
 * Address class defines the properties of an address
 */
public class Address {
    private String street;
    private String city;
    private String country;

    /**
     * Constructor for the Address class
     *
     * @param street  The street in the address
     * @param city    The city this address is located
     * @param country The country this address is located
     */
    public Address(String street, String city, String country) {
        this.setStreet(street);
        this.setCity(city);
        this.setCountry(country);
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        if ("".equals(street) || street == null) {
            throw new IllegalArgumentException("Name of street in the address cannot be set to null or empty.");
        }
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        if ("".equals(city) || city == null) {
            throw new IllegalArgumentException("Name of city in the address cannot be set to null or empty.");
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    private void setCountry(String country) {
        if ("".equals(country) || country == null) {
            throw new IllegalArgumentException("Name of country in the address cannot be set to null or empty.");
        }
        this.country = country;
    }
}
