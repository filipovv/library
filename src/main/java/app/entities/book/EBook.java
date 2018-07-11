package app.entities.book;

import java.util.Set;

public class EBook extends Book {
    private String downloadLink;
    private String readOnlineLink;

    public EBook(EBookBuilder builder) {
        super(builder);
        this.downloadLink = builder.downloadLink;
        this.readOnlineLink = builder.readOnlineLink;
    }

    public static class EBookBuilder extends Book.BookBuilder {
        private String downloadLink;
        private String readOnlineLink;

        public EBookBuilder(String title, String genre, String isbn, Set<String> tags, Set<Author> authors, String downloadLink, String readOnlineLink) {
            super(title, genre, isbn, tags, authors);
            this.setDownloadLink(downloadLink);
            this.setReadOnlineLink(readOnlineLink);
        }

        private void setDownloadLink(String downloadLink) {
            if ("".equals(downloadLink) || downloadLink == null) {
                throw new IllegalArgumentException("Download link of eBook cannot be set null or empty.");
            }
            this.downloadLink = downloadLink;
        }

        private void setReadOnlineLink(String readOnlineLink) {
            if ("".equals(downloadLink) || downloadLink == null) {
                throw new IllegalArgumentException("Read-online link of eBook cannot be set null or empty.");
            }
            this.readOnlineLink = readOnlineLink;
        }

        public EBook build() {
            return new EBook(this);
        }
    }

    public String getDownloadLink() {
        return this.downloadLink;
    }

    public String getReadOnlineLink() {
        return this.readOnlineLink;
    }
}
