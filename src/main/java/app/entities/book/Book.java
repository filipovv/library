package app.entities.book;

import java.util.HashSet;
import java.util.Set;

public abstract class Book {
    private String title;
    private String genre;
    private String isbn;
    private Set<String> tags;
    private Set<Author> authors;

    protected Book(BookBuilder builder) {
        this.title = builder.title;
        this.genre = builder.genre;
        this.isbn = builder.isbn;
        this.tags = builder.tags;
        this.authors = builder.authors;
    }

    public abstract static class BookBuilder {
        private String title;
        private String genre;
        private String isbn;
        private Set<String> tags;
        private Set<Author> authors;

        protected BookBuilder(String title, String genre, String isbn, Set<String> tags, Set<Author> authors) {
            this.setTitle(title);
            this.setGenre(genre);
            this.setIsbn(isbn);
            this.setTags(tags);
            this.setAuthors(authors);
        }

        private void setTitle(String title) {
            if ("".equals(title) || title == null) {
                throw new IllegalArgumentException("Title of the book cannot be set to null or empty.");
            }
            this.title = title;
        }

        private void setGenre(String genre) {
            if ("".equals(genre) || genre == null) {
                throw new IllegalArgumentException("Genre of the book cannot be set to null or empty.");
            }
            this.genre = genre;
        }

        private void setIsbn(String isbn) {
            if ("".equals(isbn) || isbn == null) {
                throw new IllegalArgumentException("ISBN of the book cannot be set to null or empty.");
            }
            this.isbn = isbn;
        }

        private void setTags(Set<String> tags) {
            this.tags = tags;
        }

        private void setAuthors(Set<Author> authors) {
            this.authors = authors;
        }

        public abstract Book build();
    }

    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public Set<String> getTags() {
        return new HashSet<>(this.tags);
    }

    public Set<Author> getAuthors() {
        return new HashSet<>(this.authors);
    }
}
