package app.controllers;

import app.entities.book.Book;
import app.entities.user.User;
import app.services.AuthorisationService;
import app.services.BookService;
import app.services.BorrowService;
import app.services.SearchService;

import java.util.Set;

public class BookController {
    private SearchService searchService;
    private BookService bookService;
    private BorrowService borrowService;
    private AuthorisationService authorisationService;

    public BookController(SearchService searchService, BookService bookService, BorrowService borrowService, AuthorisationService authorisationService) {
        this.searchService = searchService;
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.authorisationService = authorisationService;
    }

    public String getDownloadLink(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.bookService.getDownloadLink(user, book);
    }

    public String getOnlineLink(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        return this.bookService.getOnlineLink(user, book);
    }

    public void borrowPostponement(String sessionId, Book book, int postponeDays) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.postponeReturn(user, book, postponeDays);
    }

    public void applyForQueue(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.waitInQueue(user, book);
    }

    public void returnBook(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.returnBook(user, book);
    }

    public void borrowBook(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.borrowBook(user, book);
    }

    public Set<Book> search(String sessionId, String filter, String value) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        return this.searchService.search(filter, value);
    }

    public String getBookInfo(String sessionId, Book book) {
        if (!this.authorisationService.validateSession(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        return this.bookService.getBookInfo(book);
    }

}