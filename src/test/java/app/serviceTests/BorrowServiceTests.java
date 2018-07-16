package app.serviceTests;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;
import app.entities.book.EBook;
import app.entities.book.PaperBook;
import app.entities.enums.Gender;
import app.entities.history.BorrowBookEntry;
import app.entities.history.History;
import app.entities.user.Address;
import app.entities.user.Credentials;
import app.entities.user.User;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;
import app.repositories.QueueRepository;
import app.services.BorrowService;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BorrowServiceTests {

    @Test
    public void testIfBorrowBookMethodAddsHistoryEntry() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        QueueRepository queueRepository = new QueueRepository();
        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn", 1);

        // When
        bookRepository.addBook(book);
        borrowService.borrowBook(user, book);
        History history = historyRepository.getHistoryByUser(user);

        // Then
        assertNotNull("History should be generated for user.", history);
        assertEquals("Paper book available copies should be deducted when borrowed.", 0, ((PaperBook) book).availableCopies());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfValidatorFailThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        QueueRepository queueRepository = new QueueRepository();
        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testLink");

        bookRepository.addBook(book);
        borrowService.borrowBook(user, book);
    }

    @Test
    public void testIfWaitInQueueMethodGeneratesAQueue() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        QueueRepository queueRepository = new QueueRepository();
        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Credentials anotherCredentials = new Credentials("anotherUsername", "anotherPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        User anotherUser = new User(anotherCredentials, "anotherName", 16, Gender.FEMALE, "another@email", address, true);
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn",1);

        // When
        bookRepository.addBook(book);
        borrowService.borrowBook(user, book);
        borrowService.waitInQueue(anotherUser, book);
        BorrowQueue queue = queueRepository.findQueueByBook(book);

        // Then
        assertNotNull(queue);
        assertTrue(queueRepository.isQueueLocked(book));
    }

    @Test
    public void testIfReturnBookActuallyReturnsTheCopy() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        QueueRepository queueRepository = new QueueRepository();
        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn",1);

        // When
        bookRepository.addBook(book);
        borrowService.borrowBook(user, book);
        int borrowed = ((PaperBook) book).availableCopies();
        borrowService.returnBook(user, book);
        int returned = ((PaperBook) book).availableCopies();

        // Then
        assertNotEquals("Returning a copy should add to the available copies for a book.", borrowed, returned);
    }

    @Test
    public void testPostponementAndLockingBook() {
        // Given
        BookRepository bookRepository = new BookRepository();
        HistoryRepository historyRepository = new HistoryRepository();
        QueueRepository queueRepository = new QueueRepository();
        BorrowService borrowService = new BorrowService(bookRepository, historyRepository, queueRepository);
        Credentials credentials = new Credentials("testUsername", "testPassword");
        Address address = new Address("testStreet", "testCity", "testCountry");
        User user = new User(credentials, "testName", 15, Gender.MALE, "test@email", address, true);
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn",1);

        // When
        bookRepository.addBook(book);
        borrowService.borrowBook(user, book);
        History history = historyRepository.getHistoryByUser(user);
        BorrowBookEntry borrowBookEntry = history.findBorrowEntryByBook(book);
        LocalDate initial = borrowBookEntry.getReturnDate();
        borrowService.postponeReturn(user, book, 14);
        LocalDate afterPostponement = borrowBookEntry.getReturnDate();

        // Then
        assertNotEquals("Making postponement should add days to the return date.", initial, afterPostponement);
        assertTrue("Borrowing a book should lock the queue for the current user.", queueRepository.isQueueLocked(book));
    }
}
