package app.services;

import app.entities.book.Book;
import app.repositories.BookRepository;

import java.util.Set;

public class SearchService {
    private BookRepository bookRepository;

    public SearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set<Book> search(String filter, String... values) {
        if ("".equals(filter) || filter == null) {
            throw new IllegalArgumentException("Search filter cannot be empty or null.");
        }

        switch (filter) {
            case "title":
                return this.searchByTitle(values[0]);
            case "genre":
                return this.searchByGenre(values[0]);
            case "tag":
                return this.searchByTags(values);
            case "authorName":
                return this.searchByAuthorName(values[0]);
            default:
                return null;
        }
    }

    private Set<Book> searchByTitle(String title) {
        if ("".equals(title) || title == null) {
            throw new IllegalArgumentException("Title in search filter cannot be null or empty.");
        }
        return this.bookRepository.findByTitle(title);
    }

    private Set<Book> searchByGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            throw new IllegalArgumentException("Genre in search filter cannot be null or empty.");
        }
        return this.bookRepository.findByGenre(genre);
    }

    private Set<Book> searchByTags(String... tags) {
        if (tags.length == 0) {
            throw new IllegalArgumentException("Tags in search filter must be grater than 0.");
        }
        return this.bookRepository.findByTags(tags);
    }

    private Set<Book> searchByAuthorName(String authorName) {
        if ("".equals(authorName) || authorName == null) {
            throw new IllegalArgumentException("Name in search filter cannot be null or empty.");
        }

        String[] names = authorName.trim().split(" ");
        if (names.length == 1) {
            return this.bookRepository.findByName(authorName);
        } else {
            return this.bookRepository.findByFullName(names);
        }
    }
}