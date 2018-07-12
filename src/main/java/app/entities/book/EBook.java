package app.entities.book;

public class EBook extends Book {
    private static final String EBOOK_TYPE = "Ebook";

    private String readOnlineLink;
    private String downloadLink;

    public EBook(String title, String genre, String isbn, String readOnlineLink, String downloadLink) {
        super(title, genre, isbn);
        this.setReadOnlineLink(readOnlineLink);
        this.setDownloadLink(downloadLink);
    }

    public EBook(String title, String genre, String isbn, String readOnlineLink) {
        super(title, genre, isbn);
        this.setReadOnlineLink(readOnlineLink);
    }

    @Override
    public String getBookType() {
        return EBOOK_TYPE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("-- EBook Info --%nTitle: %s%nGenre: %s%nISBN: %s%nAuthors: %n",
                super.getTitle(), super.getGenre(), super.getIsbn()));
        for (Author author : super.getAuthors()) {
            sb.append(String.format("- Author name: %s%n", author.getName()));
        }
        sb.append(String.format("Read-online Link: %s%n", this.readOnlineLink));
        if (this.downloadLink != null) {
            sb.append(String.format("Download Link: %s%n", this.downloadLink));
        }
        return sb.toString();
    }

    public String getReadOnlineLink() {
        return this.readOnlineLink;
    }

    private void setReadOnlineLink(String readOnlineLink) {
        if ("".equals(downloadLink) || downloadLink == null) {
            throw new IllegalArgumentException("Read-online link of eBook cannot be set null or empty.");
        }
        this.readOnlineLink = readOnlineLink;
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }

    private void setDownloadLink(String downloadLink) {
        if ("".equals(downloadLink) || downloadLink == null) {
            throw new IllegalArgumentException("Download link of eBook cannot be set null or empty.");
        }
        this.downloadLink = downloadLink;
    }
}
