package app.entities.book;

public class PaperBook extends Book {
    private static final String PAPER_BOOK_TYPE = "Ebook";

    private int totalCopies;
    private int borrowedCopies;

    public PaperBook(String title, String genre, String isbn, int totalCopies) {
        super(title, genre, isbn);
        this.totalCopies = totalCopies;
        this.setBorrowedCopies();
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

    @Override
    public String getBookType() {
        return PAPER_BOOK_TYPE;
    }

    public int getTotalCopies() {
        return this.totalCopies;
    }

    private void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getBorrowedCopies() {
        return this.borrowedCopies;
    }

    private void setBorrowedCopies() {
        this.borrowedCopies = 0;
    }
}
