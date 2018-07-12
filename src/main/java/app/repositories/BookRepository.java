package app.repositories;

import app.entities.book.Book;

import java.util.HashSet;
import java.util.Set;

public class BookRepository {
    private Set<Book> books;

    public BookRepository() {
        this.books = new HashSet<>();
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot add a null as book to repository.");
        } else if (this.books.contains(book)) {
            throw new IllegalArgumentException("Book already exists in the repository.");
        }

        this.books.add(book);
    }

    public void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        } else if (!this.books.contains(book)) {
            throw new IllegalArgumentException("Book does not exist in the repository.");
        }

        this.books.remove(book);
    }

    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }
}
