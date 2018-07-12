package app;

import app.controllers.UserController;
import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.UserRepository;

public class App {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserController userController = new UserController(userRepository, profileService);
        Credentials credentials = new Credentials("myUsername", "myPassword");
        Address address = new Address("Zornitsa 2", "Plovdiv", "Bulgaria");
        User user = new User(credentials, "Vasil Filipov", 25, Gender.MALE, "filipov.v@icloud.com", address, true);

        userController.registerUser(user);
        userController.login(credentials);

        System.out.printf("Username: %s%nName: %s%n", userController.getSession().getUser().getCredentials().getUsername(), userController.getSession().getUser().getName());
    }
}
