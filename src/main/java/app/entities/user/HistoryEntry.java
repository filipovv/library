package app.entities.user;


import app.entities.book.Book;
import app.entities.enums.Status;

import java.time.LocalDate;

public class HistoryEntry {
    private Status status;
    private Book book;
    private LocalDate entryDate;

    public HistoryEntry(Status status, Book book, LocalDate entryDate) {
        this.setStatus(status);
        this.setBook(book);
        this.setEntryDate(entryDate);
    }

    public Status getStatus() {
        return status;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public Book getBook() {
        return book;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    private void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}
