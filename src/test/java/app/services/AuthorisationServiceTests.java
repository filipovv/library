package app.services;

import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class AuthorisationServiceTests {

    @Test
    public void testIfRegisterStoresUser() {
        // Given
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        authorisationService.registerUser(user);

        // Then
        assertEquals("User repository size should be altered.", 1, userRepository.getUsers().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAlreadyRegisteredThrowsAnException() {
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        authorisationService.registerUser(user);
        authorisationService.registerUser(user);
    }

    @Test
    public void testIfSessionValidatorWorks() {
        // Given
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        authorisationService.registerUser(user);
        String sessionId = authorisationService.login(credentials);

        // Then
        assertTrue("Validation should be matching", authorisationService.validateSession(sessionId));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfValidatorGivenNullThrowsAnException() {
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        authorisationService.validateSession(null);
    }

    @Test
    public void testIfLogoutDeletesCurrentSession() {
        // Given
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        authorisationService.registerUser(user);
        String sessionId = authorisationService.login(credentials);
        authorisationService.deleteCurrentSession();

        // Then
        assertNull("Logout should be able to delete the current session.", authorisationService.getSession());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfLoginWithUnknownCredentialsThrowsAnException() {
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");

        String sessionId = authorisationService.login(credentials);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfTryingToRegisterANullUserThrowsException() {
        UserRepository userRepository = new UserRepository();
        AuthorisationService authorisationService = new AuthorisationService(userRepository);
        authorisationService.registerUser(null);
    }
}
