package app.repositories;

import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class UserRepositoryTests {

    @Test
    public void testIfRemoveUserActuallyRemovesFromTheRepository() {
        // Given
        UserRepository userRepository = new UserRepository();
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        userRepository.addUser(user);
        int initial = userRepository.getUsers().size();
        userRepository.removeUser(user);
        int after = userRepository.getUsers().size();

        // Then
        assertNotEquals("Remove method in the UserRepository should actually remove users.", initial, after);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfTryingToRemoveNonexistentUserThrowsAnException() {
        UserRepository userRepository = new UserRepository();
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        userRepository.removeUser(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddingAnExistingUserThrowsAnException() {
        UserRepository userRepository = new UserRepository();
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        userRepository.addUser(user);
        userRepository.addUser(user);
    }

    @Test
    public void testIfFindUserByCredentialsReturnsCorrectUser() {
        // Given
        UserRepository userRepository = new UserRepository();
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        userRepository.addUser(user);
        User newUser = userRepository.findUserByCredentials(credentials);

        // Then
        assertEquals("findUserByCredentials should return the correct user.", user, newUser);
    }

    @Test
    public void testIfIsAlreadyRegisteredReturnsCorrect() {
        // Given
        UserRepository userRepository = new UserRepository();
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        userRepository.addUser(user);

        // Then
        assertTrue(userRepository.isAlreadyRegistered(user));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInAddUserThrowsException() {
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInRemoveUserThrowsException() {
        UserRepository userRepository = new UserRepository();
        userRepository.removeUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInFindUserThrowsException() {
        UserRepository userRepository = new UserRepository();
        userRepository.findUserByCredentials(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInIsAlreadyRegisteredThrowsException() {
        UserRepository userRepository = new UserRepository();
        userRepository.isAlreadyRegistered(null);
    }
}
