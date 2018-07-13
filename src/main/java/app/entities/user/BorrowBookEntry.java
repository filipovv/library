package app.entities.user;

import app.entities.book.Book;

import java.time.LocalDate;
import java.util.Objects;

public class BorrowBookEntry {
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowBookEntry(Book book) {
        this.setBook(book);
        this.setBorrowDate();
        this.setReturnDate();
    }

    public void postpone(int postponeDays) {
        if (postponeDays > 14 || postponeDays <= 0) {
            throw new IllegalArgumentException("Postponement must be between 1 and 14 days.");
        }

        this.returnDate = returnDate.plusDays(postponeDays);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof BorrowBookEntry)) {
            return false;
        }

        BorrowBookEntry book = (BorrowBookEntry) obj;
        boolean check = this.getBook().getTitle().equalsIgnoreCase(book.getBook().getTitle());
        return check;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getBook().getTitle());
    }

    public Book getBook() {
        return this.book;
    }

    private void setBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be set to null.");
        }
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return this.borrowDate;
    }

    private void setBorrowDate() {
        if (borrowDate == null) {
            throw new IllegalArgumentException("Borrow date cannot be set to null.");
        }
        this.borrowDate = LocalDate.now();
    }

    public LocalDate getReturnDate() {
        return this.returnDate;
    }

    private void setReturnDate() {
        if (returnDate == null) {
            throw new IllegalArgumentException("Reaturn date cannot be set to null.");
        }
        this.returnDate = getBorrowDate().plusDays(14);
    }
}
