package app.repositories;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;
import app.entities.book.PaperBook;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class QueueRepositoryTests {

    @Test
    public void testIfAddQueueActuallyAddsToTheRepository() {
        // Given
        QueueRepository queueRepository = new QueueRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testisbn", 1);
        BorrowQueue queue = new BorrowQueue((PaperBook) book);

        // When
        int initial = queueRepository.getQueues().size();
        queueRepository.addQueue(queue);
        int after = queueRepository.getQueues().size();

        // Then
        assertNotEquals(initial, after);
    }

    @Test
    public void testIfFindQueueByBookReturnsAQueue() {
        // Given
        QueueRepository queueRepository = new QueueRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testisbn", 1);
        BorrowQueue queue = new BorrowQueue((PaperBook) book);

        // When
        queueRepository.addQueue(queue);
        BorrowQueue newQueue = queueRepository.findQueueByBook(book);

        // Then
        assertEquals(queue, newQueue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfCheckingLockStatusForNonexistentBookThrowsAnException() {
        QueueRepository queueRepository = new QueueRepository();
        Book book = new PaperBook("testTitle", "testGenre", "testSummary", "testisbn", 1);
        queueRepository.isQueueLocked(book);
    }
}
