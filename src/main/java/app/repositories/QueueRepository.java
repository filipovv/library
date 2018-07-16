package app.repositories;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class QueueRepository {
    private Set<BorrowQueue> queues;

    public QueueRepository() {
        this.queues = new HashSet<>();
    }

    public boolean isQueueLocked(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book parameter cannot be null to complete operation.");
        }
        BorrowQueue queue = this.findQueueByBook(book);
        if (queue == null) {
            throw new IllegalArgumentException("No queue for this book.");
        }

        return queue.getPickBookDeadline().isAfter(LocalDate.now());
    }

    public BorrowQueue findQueueByBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book parameter cannot be null to complete operation.");
        }
        return this.queues.stream().filter(x -> x.getPaperBook().equals(book)).findFirst().orElse(null);
    }

    public void addQueue(BorrowQueue queue) {
        if (queue == null) {
            throw new IllegalArgumentException("Cannot add null to queue repository.");
        }

        this.queues.add(queue);
    }

    public Set<BorrowQueue> getQueues() {
        return this.queues;
    }
}
