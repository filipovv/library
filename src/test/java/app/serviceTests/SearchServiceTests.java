package app.serviceTests;

import app.entities.book.Author;
import app.entities.book.Book;
import app.entities.book.EBook;
import app.repositories.BookRepository;
import app.services.SearchService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SearchServiceTests {

    @Test(expected = IllegalArgumentException.class)
    public void testIfNonexistentFilterThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);

        searchService.search("testFilter", "testValues");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullFilterThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);

        searchService.search(null, "testValue");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullValueThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);

        searchService.search("title", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfEmptyFilterThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);

        searchService.search("", "testValue");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfEmptyValueThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);

        searchService.search("title", "");
    }

    @Test
    public void testIfSearchByTitleReturnsCorrect() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);
        Set<Book> books = searchService.search("title", "testTitle");
        for (Book entry : books) {
            assertEquals("Search by title should return the correct collection of books.", "testTitle", entry.getTitle());
        }
    }

    @Test
    public void testIfSearchByGenreReturnsCorrect() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);
        Set<Book> books = searchService.search("genre", "test");
        for (Book entry : books) {
            assertEquals("Search by genre should return the correct collection of books.", "testGenre", entry.getGenre());
        }
    }

    @Test
    public void testIfSearchByTagsReturnsCorrect() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        book.addTags("test", "unitTest");
        bookRepository.addBook(book);
        Set<Book> books = searchService.search("tag", "test unitTest");
        for (Book entry : books) {
            assertEquals("Search by tags should return the correct collection of books.", "testTitle", entry.getTitle());
        }
    }

    @Test
    public void testIfSearchByAuthorNameReturnsCorrect() {
        BookRepository bookRepository = new BookRepository();
        SearchService searchService = new SearchService(bookRepository);
        Author author = new Author("testName for unitTest", "testCountry", LocalDate.MIN, LocalDate.MAX);
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testReadOnlineLink", "testDownloadLink");
        book.addAuthors(author);
        bookRepository.addBook(book);
        Set<Book> books = searchService.search("authorName", "testName unitTest");
        for (Book entry : books) {
            assertEquals("Search by author name should return the correct collection of books.", "testTitle", entry.getTitle());
        }
    }
}
