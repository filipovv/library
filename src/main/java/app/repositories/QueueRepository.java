package app.repositories;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * QueueRepository class serves as a repository for objects of type BorrowQueue in the application.
 */
public class QueueRepository {
    private Set<BorrowQueue> queues;

    /**
     * Constructor for the QueueRepository.
     */
    public QueueRepository() {
        this.queues = new HashSet<>();
    }

    /**
     * Method used to define if a certain queue by a given book is currently locked for the first user in line.
     *
     * @param book Object of type Book to be used as search value.
     * @return Boolean value representing if the queue is currently locked or not.
     */
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

    /**
     * Method used to find a certain queue by a given book
     *
     * @param book Object of type Book to be used as search value
     * @return Object of type BorrowQueue representing the queue for that specific book.
     * Returns null if no such queue for this book.
     */
    public BorrowQueue findQueueByBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book parameter cannot be null to complete operation.");
        }
        return this.queues.stream().filter(x -> x.getPaperBook().equals(book)).findFirst().orElse(null);
    }

    /**
     * Method used to add a queue to the repository.
     *
     * @param queue Object of type BorrowQueue to be added to the repository.
     */
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
