package app.controllers;

import app.entities.book.Book;
import app.entities.user.User;
import app.services.AuthorisationService;
import app.services.BookService;
import app.services.BorrowService;
import app.services.SearchService;

import java.util.Set;

/**
 * BookController class serves as a controller for all operations related to operations with books.
 */
public class BookController {
    private SearchService searchService;
    private BookService bookService;
    private BorrowService borrowService;
    private AuthorisationService authorisationService;

    /**
     * Constructor for the BookController class
     *
     * @param searchService        The search service
     * @param bookService          The service for the books
     * @param borrowService        The borrow service
     * @param authorisationService The authorisation service
     */
    public BookController(SearchService searchService, BookService bookService, BorrowService borrowService, AuthorisationService authorisationService) {
        this.searchService = searchService;
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.authorisationService = authorisationService;
    }

    /**
     * Method used to get the download link for a specific book.
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book to be used as search value
     * @return
     */
    public String getDownloadLink(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.bookService.getDownloadLink(user, book);
    }

    /**
     * Method used to get the read-online link for a specific book.
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book to be used as search value
     * @return
     */
    public String getOnlineLink(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.bookService.getOnlineLink(user, book);
    }

    /**
     * Method used to provide functionality for a borrow postponement
     *
     * @param sessionId    String value of the session id, provided from the client to be verified with the current session.
     * @param book         Object of type Book to be used as search value
     * @param postponeDays Integer value to be requested as postponement days
     */
    public void borrowPostponement(String sessionId, Book book, int postponeDays) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.postponeReturn(user, book, postponeDays);
    }

    /**
     * Method used to provide functionality to apply for a queue for a book
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book for the queue
     */
    public void applyForQueue(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.waitInQueue(user, book);
    }

    /**
     * Method used to provide functionality to return an already borrowed book
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book to be returned
     */
    public void returnBook(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.returnBook(user, book);
    }

    /**
     * Method used to provide functionality to borrow an existing in the application book
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book to be borrowed
     */
    public void borrowBook(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.borrowBook(user, book);
    }

    /**
     * Method used to provide functionality to search for a book entry in the application
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param filter    String value of the filter the application will search with
     * @param value     String value to be used as search value
     * @return Collection of type Book containing the result of a specific search
     */
    public Set<Book> search(String sessionId, String filter, String value) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        return this.searchService.search(filter, value);
    }

    /**
     * Method used to provide information about a certain book
     *
     * @param sessionId String value of the session id, provided from the client to be verified with the current session.
     * @param book      Object of type Book to provide information for
     * @return String value representing the information about the book
     */
    public String getBookInfo(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        return this.bookService.getBookInfo(book);
    }

}