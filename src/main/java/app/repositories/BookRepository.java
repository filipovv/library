package app.repositories;

import app.entities.book.Author;
import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BookRepository {
    private Set<Book> books;

    public BookRepository() {
        this.books = new HashSet<>();
    }

    public Set<Book> findByName(String name) {
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            for (Author author : book.getAuthors()) {
                if (author.getName().contains(name)) {
                    result.add(book);
                    break;
                }
            }
        }
        return result;
    }

    public Set<Book> findByFullName(String... names) {
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            for (Author author : book.getAuthors()) {
                if (Arrays.stream(names).parallel().allMatch(x -> author.getName().contains(x))) {
                    result.add(book);
                    break;
                }
            }
        }
        return result;
    }

    public Set<Book> findByTags(String... tags) {
        Set<Book> result = new HashSet<>();
        for (String tag : tags) {
            if ("".equals(tag) || tag == null) {
                throw new IllegalArgumentException("Tag in search filter must not be null or empty.");
            }
            for (Book book : this.books) {
                if (book.getTags().contains(tag)) {
                    result.add(book);
                }
            }
        }
        return result;
    }

    public Set<Book> findByGenre(String genre) {
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                result.add(book);
            }
        }

        return result;
    }

    public Set<Book> findByTitle(String title) {
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            if (book.getTitle().contains(title)) {
                result.add(book);
            }
        }

        return result;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot add a null as book to repository.");
        }
        if (this.books.contains(book)) {
            if (book instanceof EBook) {
                throw new IllegalArgumentException("EBook already exists in the repository.");
            }
            Book newBook = null;
            for (Book entry : this.books) {
                if (entry.equals(book)) {
                    newBook = entry;
                    break;
                }
            }

            this.books.remove(newBook);
            ((PaperBook) newBook).addCopies(1);
            this.books.add(newBook);
        } else {
            this.books.add(book);
        }
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