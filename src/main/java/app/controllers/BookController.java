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

    public void returnBook(String sessionId, Book book) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Cannot return null as book.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.returnBook(user, book);
    }

    public void borrowBook(String sessionId, Book book) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }

        if (book == null) {
            throw new IllegalArgumentException("Book must not be null to borrow it.");
        }

        User user = this.authorisationService.getSession().getUser();
        this.borrowService.borrowBook(user, book);
    }

    public Set<Book> search(String sessionId, String filter, String... values) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
        }
        return this.searchService.search(filter, values);
    }

    public String getBookInfo(String sessionId, Book book) {
        if ("".equals(sessionId) || sessionId == null) {
            throw new IllegalArgumentException("Missing sessionId. User not logged in.");
        }

        if (!this.authorisationService.getSession().getId().equals(sessionId)) {
            throw new IllegalArgumentException("No permission to see this page. SessionId does not match.");
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
