package app.entities.book;

import java.util.Set;

public class PaperBook extends Book {
    private int totalCopies;
    private int borrowedCopies;

    public PaperBook(PaperBookBuilder builder) {
        super(builder);
        this.totalCopies = builder.totalCopies;
        this.borrowedCopies = builder.borrowedCopies;
    }

    public static class PaperBookBuilder extends Book.BookBuilder {
        private int totalCopies;
        private int borrowedCopies;

        public PaperBookBuilder(String title, String genre, String isbn, Set<String> tags, Set<Author> authors, int totalCopies, int borrowedCopies) {
            super(title, genre, isbn, tags, authors);
            this.setTotalCopies(totalCopies);
            this.setBorrowedCopies(borrowedCopies);
        }

        private void setTotalCopies(int totalCopies) {
            this.totalCopies = totalCopies;
        }

        private void setBorrowedCopies(int borrowedCopies) {
            this.borrowedCopies = borrowedCopies;
        }

        public PaperBook build() {
            return new PaperBook(this);
        }
    }

    public void addCopies(int copies) {
        this.totalCopies += copies;
    }

    public void removeCopies(int copies) {
        if (this.totalCopies < copies) {
            throw new IllegalArgumentException("Cannot remove more copies than the total amount.");
        }
        this.totalCopies -= copies;
    }

    public void borrowCopy() {
        if (this.borrowedCopies == this.totalCopies) {
            throw new IllegalArgumentException("Not enough copies left for borrow.");
        }
        this.borrowedCopies += 1;
    }

    public void returnCopy() {
        if (this.borrowedCopies == 0) {
            throw new IllegalArgumentException("Cannot return more copies than the total amount for the book.");
        }
        this.borrowedCopies -= 1;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    public int getBorrowedCopies() {
        return this.borrowedCopies;
    }
}
