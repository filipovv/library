package app.entities.book;

import app.entities.user.User;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class BorrowQueue {
    private PaperBook paperBook;
    private Queue<User> users;
    private LocalDate pickBookDeadline;

    public BorrowQueue(PaperBook paperBook) {
        this.setPaperBook(paperBook);
        this.users = new LinkedList<>();
        this.setPickBookDeadline();
    }

    public User getCurrentUser() {
        if (this.users.size() == 0) {
            throw new IllegalArgumentException("The queue is empty.");
        }

        return this.users.peek();
    }

    public void addUser(User user) {
        this.users.add(user);
        if (this.users.size() == 1) {
            this.setPickBookDeadline();
        }
    }

    public User nextUser() {
        if (this.users.size() == 0) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        User user = this.users.poll();
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
        return new LinkedList<>(users);
    }

    public LocalDate getPickBookDeadline() {
        return this.pickBookDeadline;
    }

    private void setPickBookDeadline() {
        this.pickBookDeadline = LocalDate.now().plusDays(3);
    }
}
