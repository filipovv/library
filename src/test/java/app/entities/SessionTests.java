package app.entities;

import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionTests {
    @Test
    public void testIfConstructorWorksCorrect() {
        // Given
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        Session session = new Session("testId", user);

        // Then
        assertEquals("Id parameter should match the created object's one.", "testId", session.getId());
        assertEquals("User parameter should match the created object's one", "test@email", session.getUser().getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsIdThrowsException() {
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Session session = new Session(null, user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsUserThrowsException() {
        Session session = new Session("testId", null);
    }
}
