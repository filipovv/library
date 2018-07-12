package app.controllers;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;
import app.repositories.BookRepository;
import app.services.BookService;
import app.services.SearchService;

import java.util.Set;

public class BookController {
    private BookRepository bookRepository;
    private SearchService searchService;
    private BookService bookService;

    public BookController(BookRepository bookRepository, SearchService searchService, BookService bookService) {
        this.bookRepository = bookRepository;
        this.searchService = searchService;
        this.bookService = bookService;
    }

    public Set<Book> search(String filter, String... values) {
        return this.searchService.search(filter, values);
    }

    public String getBookInfo(Book book) {
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
