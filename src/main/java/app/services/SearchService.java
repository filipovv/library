package app.services;

import app.entities.book.Book;
import app.repositories.BookRepository;

import java.util.Set;

public class SearchService {
    private BookRepository bookRepository;

    public SearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set<Book> search(String filter, String value) {
        if ("".equals(filter) || filter == null) {
            throw new IllegalArgumentException("Search filter cannot be empty or null.");
        }

        if ("".equals(value) || value == null) {
            throw new IllegalArgumentException("Search value cannot be empty or null.");
        }

        switch (filter) {
            case "title":
                return this.searchByTitle(value);
            case "genre":
                return this.searchByGenre(value);
            case "tag":
                return this.searchByTags(value);
            case "authorName":
                return this.searchByAuthorName(value);
            default:
                return null;
        }
    }

    private Set<Book> searchByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    private Set<Book> searchByGenre(String genre) {
        return this.bookRepository.findByGenre(genre);
    }

    private Set<Book> searchByTags(String tags) {
        String[] tagArray = tags.trim().split(" ");
        return this.bookRepository.findByTags(tagArray);
    }

    private Set<Book> searchByAuthorName(String authorName) {
        String[] names = authorName.trim().toLowerCase().split(" ");
        return this.bookRepository.findByName(names);
    }
}