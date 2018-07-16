package app;

import app.controllers.BookController;
import app.controllers.UserController;
import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;
import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;
import app.repositories.QueueRepository;
import app.repositories.UserRepository;
import app.services.*;

public class App {
    public static void main(String[] args) {
//        BookRepository bookRepository = new BookRepository();
//        HistoryRepository historyRepository = new HistoryRepository();
//        QueueRepository queueRepository = new QueueRepository();
//        UserRepository userRepository = new UserRepository();
//        AuthorisationService authorisationService = new AuthorisationService(userRepository);
//        BookService bookService = new BookService(bookRepository, historyRepository);
//        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
//        SearchService searchService = new SearchService(bookRepository);
//        UserService userService = new UserService(historyRepository);
//        BookController bookController = new BookController(searchService, bookService, borrowService, authorisationService);
//        UserController userController = new UserController(userService, authorisationService);
//
//
//        Book eBook = new EBook("lotr", "fiction", "bla bla", "634627", "www.readme.com", "dshglqjf");
//        Book paperBook1 = new PaperBook("Hobbit", "fiction", "bla bla", "634627", 1);
//        Book paperBook2 = new PaperBook("LOTR", "fiction", "bla bla", "76523", 1);
//
//        Credentials credentials1 = new Credentials("username1", "password1");
//        Credentials credentials2 = new Credentials("username2", "password2");
//        Credentials credentials3 = new Credentials("username3", "password3");
//        Credentials credentials4 = new Credentials("username4", "password4");
//
//        Address address1 = new Address("zornitsa1", "Plovdiv", "Bulgaria");
//        Address address2 = new Address("zornitsa2", "Plovdiv", "Bulgaria");
//        Address address3 = new Address("zornitsa3", "Plovdiv", "Bulgaria");
//        Address address4 = new Address("zornitsa4", "Plovdiv", "Bulgaria");
//
//        User user1 = new User(credentials1, "Vasil1", 25, Gender.MALE, "sbgfkdsgnf", address1, true);
//        User user2 = new User(credentials2, "Vasil2", 25, Gender.MALE, "gsdgs", address2, true);
//        User user3 = new User(credentials3, "Vasil3", 25, Gender.MALE, "sbgfkhgfjfgdsgnf", address3, true);
//        User user4 = new User(credentials4, "Vasil4", 25, Gender.MALE, "sbgfkasddsgnf", address4, true);
//
//        bookRepository.addBook(eBook);
//        bookRepository.addBook(paperBook1);
//
//        userController.registerUser(user1);
//        userController.registerUser(user2);
//        userController.registerUser(user3);
//        userController.registerUser(user4);
//
//        String debug = "";
//
//        userController.login(credentials2);
//        userController.logout(authorisationService.getSession().getId());
//        userController.login(credentials1);
//        userController.logout(authorisationService.getSession().getId());
//        userController.login(credentials3);
//        userController.logout(authorisationService.getSession().getId());
//        userController.login(credentials4);
//        System.out.println(userController.getUserInfo(authorisationService.getSession().getId()));
//
//        System.out.println(bookController.getBookInfo(authorisationService.getSession().getId(), paperBook1));
//        bookController.borrowBook(authorisationService.getSession().getId(), paperBook1);
//        bookController.getOnlineLink(authorisationService.getSession().getId(), eBook);
//        bookController.getDownloadLink(authorisationService.getSession().getId(), eBook);
//        bookController.getOnlineLink(authorisationService.getSession().getId(), eBook);
//        userController.logout(authorisationService.getSession().getId());
//
//        userController.login(credentials1);
//        bookController.getOnlineLink(authorisationService.getSession().getId(), eBook);
//        bookController.getDownloadLink(authorisationService.getSession().getId(), eBook);
//        bookController.getOnlineLink(authorisationService.getSession().getId(), eBook);
//        System.out.println(bookController.getBookInfo(authorisationService.getSession().getId(), paperBook1));
//        bookController.applyForQueue(authorisationService.getSession().getId(), paperBook1);
//        userController.logout(authorisationService.getSession().getId());
//
//        userController.login(credentials2);
//        bookController.applyForQueue(authorisationService.getSession().getId(), paperBook1);
//        userController.logout(authorisationService.getSession().getId());
//
//        userController.login(credentials4);
//        bookController.borrowPostponement(authorisationService.getSession().getId(), paperBook1, 14);
//        bookController.returnBook(authorisationService.getSession().getId(), paperBook1);
    }
}
