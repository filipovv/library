package app.repositories;

import app.entities.book.Author;
import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * BookRepository class serves as a repository for objects of type book in the application.
 */
public class BookRepository {
    private Set<Book> books;

    /**
     * Constructor for the BookRepository class.
     */
    public BookRepository() {
        this.books = new HashSet<>();
    }

    /**
     * Method used to find all book entries by an author's name/s.
     *
     * @param names String varargs value to be used as search value.
     * @return Collection of type Set containing the result of the search.
     */
    public Set<Book> findByName(String... names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("To search with names you need more than 1 name to search with.");
        }
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            for (Author author : book.getAuthors()) {
                if (Arrays.stream(names).allMatch(x -> author.getName().toLowerCase().contains(x))) {
                    result.add(book);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Method used to find all book entries in the repository by their tag/s.
     *
     * @param tags String varargs value to be used as search values.
     * @return Collection of type Set containing the result of the search.
     */
    public Set<Book> findByTags(String... tags) {
        if (tags.length == 0) {
            throw new IllegalArgumentException("To search with tags you need more than 1 tag to search with.");
        }
        Set<Book> result = new HashSet<>();
        for (String tag : tags) {
            if ("".equals(tag) || tag == null) {
                throw new IllegalArgumentException("Tag in search filter must not be null or empty.");
            }
            for (Book book : this.books) {
                if (book.getTags().stream().anyMatch(x -> x.toLowerCase().contains(tag.toLowerCase()))) {
                    result.add(book);
                }
            }
        }
        return result;
    }

    /**
     * Method used to find all book entries in the repository by their genre.
     *
     * @param genre String value to be used as search value.
     * @return Collection of type Set containing the result of the search.
     */
    public Set<Book> findByGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            throw new IllegalArgumentException("Genre in search filter must not be null or empty.");
        }
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            if (book.getGenre().toLowerCase().equalsIgnoreCase(genre.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Method used to find all book entries in the repository by their title.
     *
     * @param title String value to be used as search value.
     * @return Collection of type Set containing the result of the search.
     */
    public Set<Book> findByTitle(String title) {
        if ("".equals(title) || title == null) {
            throw new IllegalArgumentException("Title in search filter must not be null or empty.");
        }
        Set<Book> result = new HashSet<>();
        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return result;
    }

    /**
     * Method used to ass book entries into the repository.
     *
     * @param book Object of type Book to be added in the repository.
     */
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

            if (newBook != null) {
                this.books.remove(newBook);
                ((PaperBook) newBook).addCopies(((PaperBook) book).getTotalCopies());
                this.books.add(newBook);
            }
        } else {
            this.books.add(book);
        }
    }

    /**
     * Method used to remove a book entry from the repository.
     *
     * @param book Object of type Book to be removed from the repository.
     */
    public void removeBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        } else if (!this.books.contains(book)) {
            throw new IllegalArgumentException("Book does not exist in the repository.");
        }

        this.books.remove(book);
    }

    public Set<Book> getBooks() {
        return this.books;
    }
}