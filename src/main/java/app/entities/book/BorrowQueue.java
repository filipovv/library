package app.entities.book;

import app.entities.user.User;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BorrowQueue class provides properties and functionality for borrowing queue type
 */
public class BorrowQueue {
    private PaperBook paperBook;
    private Queue<User> users;
    private LocalDate pickBookDeadline;

    /**
     * Constructor for the BorrowQueue class
     *
     * @param paperBook The paper book this queue is opened for
     */
    public BorrowQueue(PaperBook paperBook) {
        this.setPaperBook(paperBook);
        this.users = new LinkedList<>();
        this.setPickBookDeadline();
    }

    /**
     * Method used to check if the current queue for this book is locked. Defined by the pickBookDeadline property.
     *
     * @return Boolean value, representing if the queue is locked or not
     */
    public boolean isQueueLocked() {
        return this.pickBookDeadline.isBefore(LocalDate.now());
    }

    /**
     * Method used to get the user who currently is borrowing the book.
     *
     * @return Object of type User, representing the current user using the book.
     */
    public User getCurrentUser() {
        if (this.users.size() == 0) {
            throw new IllegalArgumentException("The queue is empty.");
        }

        return this.users.peek();
    }

    /**
     * Method used to add a User to the queue
     *
     * @param user Object of type User to be added to the queue
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Cannot add null as user to queue.");
        }
        this.users.add(user);
        if (this.users.size() == 1) {
            this.setPickBookDeadline();
        }
    }

    /**
     * Method used to shift the queue up. Current user is the next one in line.
     *
     * @return Object of type User representing the new current user.
     */
    public User nextUser() {
        if (this.users.size() == 0) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        this.users.poll();
        User user = this.users.peek();
        if (this.users.size() != 0) {
            this.setPickBookDeadline();
        }
        return user;
    }

    public PaperBook getPaperBook() {
        return this.paperBook;
    }

    private void setPaperBook(PaperBook paperBook) {
        if (paperBook == null) {
            throw new IllegalArgumentException("Paper Book cannot be null.");
        }
        this.paperBook = paperBook;
    }

    public Queue<User> getUsers() {
        return this.users;
    }

    public LocalDate getPickBookDeadline() {
        return this.pickBookDeadline;
    }

    private void setPickBookDeadline() {
        this.pickBookDeadline = LocalDate.now().plusDays(3);
    }
}
