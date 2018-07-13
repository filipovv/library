package app.repositories;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;
import app.entities.book.PaperBook;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class QueueRepository {
    private Set<BorrowQueue> queues;

    public QueueRepository(Set<BorrowQueue> queues) {
        this.queues = queues;
    }

    public boolean isQueueLocked(Book book) {
        BorrowQueue queue = this.findQueueByBook(book);
        if (queue == null) {
            throw new IllegalArgumentException("No queue for this book.");
        }

        return queue.getPickBookDeadline().isAfter(LocalDate.now());
    }

    public BorrowQueue findQueueByBook(Book book) {
        return this.queues.stream().filter(x -> x.getPaperBook().equals(book)).findFirst().orElse(null);
    }

    public Set<BorrowQueue> getQueues() {
        return new HashSet<>(this.queues);
    }
}
