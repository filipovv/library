package app.entities.history;

import app.entities.book.Book;
import app.entities.enums.Status;
import app.entities.user.User;

import java.time.LocalDate;
import java.util.*;

/**
 * History class defines the properties and functionality of an object of type History.
 * Provides information about the user, his completed history and the currently borrowed books.
 */
public class History {
    private User user;
    private List<HistoryEntry> historyEntries;
    private Set<BorrowBookEntry> currentlyBorrowed;

    /**
     * Constructor for the History class
     *
     * @param user This is the user this history is for
     */
    public History(User user) {
        this.setUser(user);
        this.historyEntries = new LinkedList<>();
        this.currentlyBorrowed = new HashSet<>();
    }

    /**
     * Method used to move a currently borrowed book the the completed history
     *
     * @param book Object of type Book to be moved for cirrently borrowed to a history entry.
     */
    public void returnBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book trying to return cannot be null.");
        }

        BorrowBookEntry entry = this.findBorrowEntryByBook(book);
        if (entry == null) {
            throw new IllegalArgumentException("Book not borrowed by this user.");
        }
        HistoryEntry historyEntry = new HistoryEntry(Status.BORROWED, book, LocalDate.now());
        this.currentlyBorrowed.remove(entry);
        this.historyEntries.add(historyEntry);
    }

    /**
     * Method used to add an entry to the completed history
     *
     * @param book   Object of type Book to be added to the completed history
     * @param status Status enumeration for the type of the history entry
     */
    public void addToHistory(Book book, Status status) {
        if (book == null) {
            throw new IllegalArgumentException("Book trying to add in history cannot be null.");
        }

        HistoryEntry entry = new HistoryEntry(status, book, LocalDate.now());
        this.historyEntries.add(entry);
    }

    /**
     * Method used to add a book to the currently borrowed
     *
     * @param book Object of type Book to be added to the currently borrowed
     */
    public void borrowBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book trying to borrow cannot be null.");
        }
        BorrowBookEntry entry = this.findBorrowEntryByBook(book);
        if (entry != null) {
            throw new IllegalArgumentException("Book already borrowed.");
        }
        entry = new BorrowBookEntry(book);
        this.currentlyBorrowed.add(entry);
    }

    /**
     * Method used to find a borrow entry in the currently borrowed by a book
     *
     * @param book Object of type Book to be used as search param
     * @return BorrowBookEntry type representing the borrow entry in the currently borrowed books
     */
    public BorrowBookEntry findBorrowEntryByBook(Book book) {
        BorrowBookEntry borrowBookEntry = this.currentlyBorrowed.stream()
                .filter(x -> x.getBook().equals(book))
                .findFirst()
                .orElse(null);

        return borrowBookEntry;
    }

    public User getUser() {
        return this.user;
    }

    private void setUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User in history cannot be set to null.");
        }
        this.user = user;
    }

    public List<HistoryEntry> getHistoryEntries() {
        return this.historyEntries;
    }
}