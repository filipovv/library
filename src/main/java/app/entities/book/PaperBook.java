package app.entities.book;

/**
 * Class PaperBook implements the properties and the functionality of a paper type book.
 * Derived class of Book.class
 */
public class PaperBook extends Book {
    private static final String PAPER_BOOK_TYPE = "PaperBook";
    private int totalCopies;
    private int borrowedCopies;

    /**
     * Constructor for the EBook class. Uses the constructor from the base class.
     *
     * @param title       The title of the book
     * @param genre       The genre of the book
     * @param summary     A short summary of the book
     * @param isbn        The ISBN of the book
     * @param totalCopies Total copies available for this book
     */
    public PaperBook(String title, String genre, String summary, String isbn, int totalCopies) {
        super(title, genre, summary, isbn);
        this.totalCopies = totalCopies;
        this.setBorrowedCopies();
    }

    /**
     * Method used to provide information of the currently available copies of this book
     *
     * @return Integer value, representing the number of books currently available.
     */
    public int availableCopies() {
        return this.totalCopies - this.borrowedCopies;
    }

    /**
     * Method used to add copies to the total amount of the current book
     *
     * @param copies Integer value to be added to the total number of copies
     */
    public void addCopies(int copies) {
        this.totalCopies += copies;
    }

    /**
     * Method used to remove a number of copies from the total amount for this book
     *
     * @param copies Integer value to be deducted from the total amount
     */
    public void removeCopies(int copies) {
        if (this.totalCopies < copies) {
            throw new IllegalArgumentException("Cannot remove more copies than the total amount.");
        }
        this.totalCopies -= copies;
    }

    /**
     * Method used to increase the currently borrowed books by 1
     */
    public void borrowCopy() {
        if (this.borrowedCopies == this.totalCopies) {
            throw new IllegalArgumentException("Not enough copies left for borrow.");
        }
        this.borrowedCopies += 1;
    }

    /**
     * Method used to deduct the currently borrowed copies by 1
     */
    public void returnCopy() {
        if (this.borrowedCopies == 0) {
            throw new IllegalArgumentException("Cannot return more copies than the total amount for the book.");
        }
        this.borrowedCopies -= 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBookType() {
        return PAPER_BOOK_TYPE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("-- PaperBook Info --%nTitle: %s%nGenre: %s%nISBN: %s%nAuthors: %n",
                super.getTitle(), super.getGenre(), super.getIsbn()));
        for (Author author : super.getAuthors()) {
            sb.append(String.format("- Author name: %s%n", author.getName()));
        }
        sb.append("Availability for borrow: ");
        if (totalCopies > borrowedCopies) {
            sb.append("Yes! Request now!");
        } else {
            sb.append("No. Must wait in queue!");
        }
        return sb.toString();
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
