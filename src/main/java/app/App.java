package app;

import app.controllers.BookController;
import app.controllers.UserController;
import app.entities.book.Author;
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

import java.time.LocalDate;
import java.util.Set;

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
//        UserService userService = new UserService(historyRepository, userRepository);
//        BookController bookController = new BookController(searchService, bookService, borrowService, authorisationService);
//        UserController userController = new UserController(userService, authorisationService);
//
//        String id = null;
//        Author author = new Author("Tolkien", "Belgium", LocalDate.of(1896, 3, 16));
//
//
//        Book eBook = new EBook("lotr", "fiction", "bla bla", "634627", "www.readme.com", "dshglqjf");
//        eBook.addTags("smeagol", "gandalf", "tolkien", "lord", "ring");
//        eBook.addAuthors(author);
//        Book paperBook1 = new PaperBook("Hobbit", "fiction", "bla bla", "634627", 1);
//        paperBook1.addTags("smeagol", "gandalf", "tolkien", "ring");
//        paperBook1.addAuthors(author);
//        Book paperBook2 = new PaperBook("LOTR", "fiction", "bla bla", "76523", 1);
//        paperBook2.addTags("smeagol", "gandalf", "tolkien", "lord", "ring");
//        paperBook2.addAuthors(author);
//
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
//        bookRepository.addBook(paperBook2);
//
//        userController.registerUser(user1);
//        userController.registerUser(user2);
//        userController.registerUser(user3);
//        userController.registerUser(user4);
//
//        String debug = "";
//
//        id = userController.login(credentials2);
//        userController.logout(authorisationService.getSession().getId());
//        id = userController.login(credentials1);
//        userController.logout(authorisationService.getSession().getId());
//        id = userController.login(credentials3);
//        userController.logout(authorisationService.getSession().getId());
//        id = userController.login(credentials4);
//        System.out.println(userController.getUserInfo(id));
//
//        System.out.println(bookController.getBookInfo(id, paperBook1));
//        bookController.borrowBook(id, paperBook1);
//        bookController.getOnlineLink(id, eBook);
//        bookController.getDownloadLink(id, eBook);
//        bookController.getOnlineLink(id, eBook);
//        userController.logout(id);
//
//        id = userController.login(credentials1);
//        bookController.getOnlineLink(id, eBook);
//        bookController.getDownloadLink(id, eBook);
//        bookController.getOnlineLink(id, eBook);
//        System.out.println(bookController.getBookInfo(id, paperBook1));
//        bookController.applyForQueue(id, paperBook1);
//        userController.logout(id);
//
//        id = userController.login(credentials2);
//        bookController.applyForQueue(id, paperBook1);
//        userController.logout(id);
//
//        id = userController.login(credentials4);
//        bookController.borrowPostponement(id, paperBook1, 14);
//        bookController.returnBook(id, paperBook1);
//
//        Set<Book> books = bookController.search(id, "authorName", "tolkien");
//        for (Book book : books) {
//            System.out.println(book.getTitle());
//        }
    }
}
