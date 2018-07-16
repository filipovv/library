package app.services;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.enums.Gender;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookServiceTests {

    @Test(expected = IllegalArgumentException.class)
    public void testIfValidatorThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        BookService bookService = new BookService(bookRepository, historyRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");

        bookService.getOnlineLink(user, book);
    }

    @Test
    public void testIfGetReadOnlineLinkWorks() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        BookService bookService = new BookService(bookRepository, historyRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);

        // When
        String returnedString = bookService.getOnlineLink(user, book);

        // Then
        assertEquals("Get Online Link does not return the correct link", "testReadOnlineLink", returnedString);
    }

    @Test
    public void testIfGetDownloadLinkWorks() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        BookService bookService = new BookService(bookRepository, historyRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);

        // When
        String returnedString = bookService.getDownloadLink(user, book);

        // Then
        assertEquals("Get Download Link does not return the correct link", "testDownloadLink", returnedString);
    }

    @Test
    public void testIfGetBookInfoReturnsCorrectInfo() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        BookService bookService = new BookService(bookRepository, historyRepository);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);

        // When
        String result = bookService.getBookInfo(book);

        // Then
        assertEquals("getBookInfo method does not return the correct info", result, book.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfTryingToGetLinkFromNotDownloadableBookThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        BookService bookService = new BookService(bookRepository, historyRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink");

        bookService.getDownloadLink(user, book);
    }
}
