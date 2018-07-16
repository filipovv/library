package app.entities.enums;

/**
 * Enumeration for the status of a book entry in the user history
 */
public enum Status {
    VIEWED("Viewed"), DOWNLOADED("Downloaded"), BORROWED("Borrowed");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
