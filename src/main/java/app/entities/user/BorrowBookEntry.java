package app.entities.user;

import app.entities.book.Book;

import java.time.LocalDate;

public class BorrowBookEntry {
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowBookEntry(Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.setBook(book);
        this.setBorrowDate(borrowDate);
        this.setReturnDate(returnDate);
    }

    public void postpone() {
        // TODO;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO;

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        // TODO;

        return super.hashCode();
    }

    public Book getBook() {
        return this.book;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    private void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    private void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
