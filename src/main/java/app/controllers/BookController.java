package app.controllers;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;
import app.entities.user.User;
import app.repositories.BookRepository;
import app.services.AuthorisationService;
import app.services.BookService;
import app.services.BorrowService;
import app.services.SearchService;

import java.util.Set;

public class BookController {
    private BookRepository bookRepository;
    private SearchService searchService;
    private BookService bookService;
    private BorrowService borrowService;
    private AuthorisationService authorisationService;

    public BookController(BookRepository bookRepository, SearchService searchService, BookService bookService, BorrowService borrowService, AuthorisationService authorisationService) {
        this.bookRepository = bookRepository;
        this.searchService = searchService;
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.authorisationService = authorisationService;
    }

    public void borrowPostponement(String sessionId, Book book, int postponeDays) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Cannot return null as book.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.postponeReturn(user, book, postponeDays);
    }

    public void applyForQueue(String sessionId, Book book) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Cannot return null as book.");
        }
        User user = this.authorisationService.getSession().getUser();
        this.borrowService.waitInQueue(user, book);
    }

    public void returnBook(String sessionId, Book book) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Cannot return null as book.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.returnBook(user, book);
    }

    public void borrowBook(String sessionId, Book book) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book must not be null to borrow it.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.borrowBook(user, book);
    }

    public Set<Book> search(String sessionId, String filter, String value) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        return this.searchService.search(filter, value);
    }

    public String getBookInfo(String sessionId, Book book) {
        if (!this.authorisationService.validateId(sessionId)) {
            throw new IllegalArgumentException("Session Id validation failed.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book must not be null to get info for it.");
        }

        if (book instanceof EBook) {
            return this.bookService.getEBookInfo((EBook) book);
        } else {
            return this.bookService.getPaperBookInfo((PaperBook) book);
        }
    }

}
