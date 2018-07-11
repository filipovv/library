package app.entities.user;

import app.entities.book.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHistory {
    private User user;
    private List<HistoryEntry> historyEntries;
    private Set<BorrowBookEntry> currentlyBorrowed;

    public UserHistory(User user) {
        this.setUser(user);
    }

    public void returnBook(Book book) {
        // TODO;
    }

    public void addToHistory(Book book) {
        // TODO;
    }

    public void borrowBook(Book book) {
        // TODO;
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
        return new ArrayList<>(this.historyEntries);
    }

    public Set<BorrowBookEntry> getCurrentlyBorrowed() {
        return new HashSet<>(this.currentlyBorrowed);
    }
}