package app.entities.user;

import app.entities.book.Book;
import app.entities.enums.Status;

import java.time.LocalDate;
import java.util.*;

public class UserHistory {
    private User user;
    private List<HistoryEntry> historyEntries;
    private Set<BorrowBookEntry> currentlyBorrowed;

    public UserHistory(User user) {
        this.setUser(user);
        this.historyEntries = new LinkedList<>();
        this.currentlyBorrowed = new HashSet<>();
    }

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

    public void addToHistory(Book book, Status status) {
        if (book == null) {
            throw new IllegalArgumentException("Book trying to add in history cannot be null.");
        }

        HistoryEntry entry = new HistoryEntry(status, book, LocalDate.now());
        this.historyEntries.add(entry);
    }

    public void borrowBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book trying to borrow cannot be null.");
        }
        BorrowBookEntry entry = this.findBorrowEntryByBook(book);
        if (entry != null) {
            throw new IllegalArgumentException("Book already borrowed.");
        }
        entry = new BorrowBookEntry(book, LocalDate.now(), LocalDate.now().plusDays(14));
        this.currentlyBorrowed.add(entry);
    }

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
        return new LinkedList<>(this.historyEntries);
    }

    public Set<BorrowBookEntry> getCurrentlyBorrowed() {
        return new HashSet<>(this.currentlyBorrowed);
    }
}