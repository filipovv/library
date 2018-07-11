package app.entities.enums;

public enum Status {
    VIEWED("Viewed"), DOWNLOADED("Downloaded"), BORROWED("Borrowed");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
