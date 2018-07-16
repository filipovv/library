package app.entities.book;

/**
 * Class EBook implements the properties and the functionality of a electronic type book.
 * Derived class of Book.class
 */
public class EBook extends Book {
    private static final String EBOOK_TYPE = "Ebook";
    private String readOnlineLink;
    private String downloadLink;

    /**
     * Constructor for the EBook class. Uses the constructor from the base class.
     *
     * @param title          The title of the book
     * @param genre          The genre of the book
     * @param summary        A short summary of the book
     * @param isbn           The ISBN of the book
     * @param readOnlineLink A link for online reading
     * @param downloadLink   A link for downloading
     */
    public EBook(String title, String genre, String summary, String isbn, String readOnlineLink, String downloadLink) {
        super(title, genre, summary, isbn);
        this.setReadOnlineLink(readOnlineLink);
        this.setDownloadLink(downloadLink);
    }

    /**
     * Constructor for the EBook class. Uses the constructor from the base class.
     *
     * @param title          The title of the book
     * @param genre          The genre of the book
     * @param summary        A short summary of the book
     * @param isbn           The ISBN of the book
     * @param readOnlineLink A link for online reading
     */
    public EBook(String title, String genre, String summary, String isbn, String readOnlineLink) {
        super(title, genre, summary, isbn);
        this.setReadOnlineLink(readOnlineLink);
    }

    /**
     * {@inheritDoc}
     */
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
        if ("".equals(readOnlineLink) || readOnlineLink == null) {
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
