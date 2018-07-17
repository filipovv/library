package app.entities.history;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.enums.Status;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class HistoryEntryTests {
    @Test
    public void testIfConstructorWorkCorrectly() {
        // Given
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testLink");

        // When
        HistoryEntry historyEntry = new HistoryEntry(Status.VIEWED, book, LocalDate.now());

        // Then
        assertEquals(Status.VIEWED, historyEntry.getStatus());
        assertEquals("testTitle", historyEntry.getBook().getTitle());
        assertEquals(LocalDate.now(), historyEntry.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsStatusThrowsException() {
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testLink");
        HistoryEntry historyEntry = new HistoryEntry(null, book, LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsBookThrowsException() {
        HistoryEntry historyEntry = new HistoryEntry(Status.VIEWED, null, LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfNullAsDateThrowsException() {
        Book book = new EBook("testTitle", "testGenre", "testSummary", "testIsbn", "testLink");
        HistoryEntry historyEntry = new HistoryEntry(Status.VIEWED, book, null);
    }
}
