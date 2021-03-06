package app.entities.history;


import app.entities.book.Book;
import app.entities.enums.Status;

import java.time.LocalDate;

/**
 * HistoryEntry class provides properties for a completed history entry in the history of an user
 */
public class HistoryEntry {
    private Status status;
    private Book book;
    private LocalDate entryDate;

    /**
     * Constructor for the HistoryEntry class
     *
     * @param status    The status of the history entry
     * @param book      The book to be put in the history entry
     * @param entryDate A history log date for the current entry
     */
    HistoryEntry(Status status, Book book, LocalDate entryDate) {
        this.setStatus(status);
        this.setBook(book);
        this.setEntryDate(entryDate);
    }

    public Status getStatus() {
        return this.status;
    }

    private void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status of history entry cannot be set to null.");
        }
        this.status = status;
    }

    public Book getBook() {
        return this.book;
    }

    private void setBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book in history cannot be set to null.");
        }
        this.book = book;
    }

    public LocalDate getEntryDate() {
        return this.entryDate;
    }

    private void setEntryDate(LocalDate entryDate) {
        if (entryDate == null) {
            throw new IllegalArgumentException("Entry date in history cannot be set to null.");
        }
        this.entryDate = entryDate;
    }
}
