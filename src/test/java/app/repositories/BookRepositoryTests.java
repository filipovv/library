package app.repositories;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.book.PaperBook;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class BookRepositoryTests {

    @Test
    public void testIfRemoveMethodActuallyRemovesBook() {
        // Given
        BookRepository bookRepository = new BookRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn", 1);

        // When
        bookRepository.addBook(book);
        int before = bookRepository.getBooks().size();
        bookRepository.removeBook(book);
        int after = bookRepository.getBooks().size();

        // Then
        assertNotEquals("Removing a book from the repository should deduct the size.", before, after);
    }

    @Test
    public void testAddingCopiesToExistingPaperBook() {
        // Given
        BookRepository bookRepository = new BookRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn", 1);

        // When
        bookRepository.addBook(book);
        int initial = ((PaperBook) book).getTotalCopies();
        bookRepository.addBook(book);
        int after = 0;
        for (Book entry : bookRepository.getBooks()) {
            if (entry.equals(book)) {
                after = ((PaperBook) entry).getTotalCopies();
            }
        }

        // Then
        assertNotEquals("Adding a copy to an existing book entry should add to the total copies of the book.", initial, after);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfTryingToAddAnExistingEBookThrowsAnException() {
        BookRepository bookRepository = new BookRepository();
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testOnlineLink", "testDownloadLink");
        bookRepository.addBook(book);
        bookRepository.addBook(book);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInFindByAuthorNameThrowsAndException() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.findByName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInFindByGenreThrowsAndException() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.findByGenre(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInFindByTagsThrowsAndException() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.findByTags(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsParamInFindByTitleThrowsAndException() {
        BookRepository bookRepository = new BookRepository();
        bookRepository.findByTitle(null);
    }

    @Test
    public void testIfRemoveBookDeductsTotalCopies() {
        // Given
        BookRepository bookRepository = new BookRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn", 5);
        Book bookToRemove = new PaperBook("testTitle", "testGenre", "testSummary", "testIsbn", 3);

        // When
        bookRepository.addBook(book);
        int initial = ((PaperBook) book).getTotalCopies();
        bookRepository.removeBook(bookToRemove);
        int after = 0;
        for (Book entry : bookRepository.getBooks()) {
            if (entry.equals(book)) {
                after = ((PaperBook) entry).getTotalCopies();
            }
        }

        // Then
        assertNotEquals(initial, after);
    }
}
