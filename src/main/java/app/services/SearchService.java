package app.services;

import app.entities.book.Book;
import app.repositories.BookRepository;

import java.util.Set;

/**
 * SearchService class provides functionality to search all the book entries
 * in the application by their title, genre, author name, tags.
 */
public class SearchService {
    private BookRepository bookRepository;

    /**
     * Constructor for the Search Service
     *
     * @param bookRepository The repository for the books
     */
    public SearchService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Method used to separate the search filters and use the corresponding methods for each of them.
     *
     * @param filter String value to be used as the filter for the search
     * @param value  String value to be used as the search values
     * @return
     */
    public Set<Book> search(String filter, String value) {
        if ("".equals(filter) || filter == null) {
            throw new IllegalArgumentException("Search filter cannot be empty or null.");
        }

        if ("".equals(value) || value == null) {
            throw new IllegalArgumentException("Search value cannot be empty or null.");
        }

        switch (filter.toLowerCase()) {
            case "title":
                return this.searchByTitle(value);
            case "genre":
                return this.searchByGenre(value);
            case "tag":
                return this.searchByTags(value);
            case "authorname":
                return this.searchByAuthorName(value);
            default:
                throw new IllegalArgumentException("No such filter.");
        }
    }

    /**
     * Method used to find all books by their title matching the provided one
     *
     * @param title String value to be used as search value
     * @return Collection of type Set containing the search result
     */
    private Set<Book> searchByTitle(String title) {
        return this.bookRepository.findByTitle(title);
    }

    /**
     * Method used to find all books by their genre matching the provided one
     *
     * @param genre String value to be used as the search parameter
     * @return Collection of type Set containing the search result
     */
    private Set<Book> searchByGenre(String genre) {
        return this.bookRepository.findByGenre(genre);
    }

    /**
     * Method used to find all books by their tags matching the provided ones
     *
     * @param tags String value containing all the tags as search parameters
     * @return Collection of type Set containing the search result
     */
    private Set<Book> searchByTags(String tags) {
        String[] tagArray = tags.trim().split(" ");
        return this.bookRepository.findByTags(tagArray);
    }

    /**
     * Method used to find all books by their authors matching the provided one
     *
     * @param authorName String value to be used as a search parameter
     * @return Collection of type Set containing the search result
     */
    private Set<Book> searchByAuthorName(String authorName) {
        String[] names = authorName.trim().toLowerCase().split(" ");
        return this.bookRepository.findByName(names);
    }
}