package app.services;

import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.HistoryRepository;
import app.repositories.UserRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTests {

    @Test
    public void testIfGetUserInfoReturnsCorrectInfo() {
        // Given
        HistoryRepository historyRepository = new HistoryRepository();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(historyRepository, userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        userRepository.addUser(user);
        String userInfo = userService.getUserInfo(user);

        // Then
        assertEquals("getUserInfo method should return the correct info.", user.toString(), userInfo);
    }

    @Test
    public void testIfGetHistoryByUserReturnsHistory() {
        // Given
        HistoryRepository historyRepository = new HistoryRepository();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(historyRepository, userRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);

        // When
        userRepository.addUser(user);
        String history = userService.getHistoryByUser(user);

        // Then
        assertNotNull("getHistoryByUser return value should not be null", history);
        assertNotEquals("getHistoryByUser should not return an empty String.", "", history);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfGetInfoForNonexistentUserThrowsException() {
        HistoryRepository historyRepository = new HistoryRepository();
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(historyRepository, userRepository);

        String history = userService.getHistoryByUser(null);
    }
}
