package app.entities.book;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Book {
    private String title;
    private String genre;
    private String isbn;
    private Set<String> tags;
    private Set<Author> authors;

    protected Book(String title, String genre, String isbn) {
        this.setTitle(title);
        this.setGenre(genre);
        this.setIsbn(isbn);
        this.tags = new HashSet<>();
        this.authors = new HashSet<>();
    }

    public abstract String getBookType();

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Book)) {
            return false;
        }

        Book book = (Book) obj;
        boolean check = this.getTitle().equals(book.getTitle()) && this.getBookType().equals(book.getBookType());
        return check;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getTitle(), this.getBookType());
    }

    public String getTitle() {
        return this.title;
    }

    private void setTitle(String title) {
        if ("".equals(title) || title == null) {
            throw new IllegalArgumentException("Title of the book cannot be set to null or empty.");
        }
        this.title = title;
    }

    public String getGenre() {
        return this.genre;
    }

    private void setGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            throw new IllegalArgumentException("Genre of the book cannot be set to null or empty.");
        }
        this.genre = genre;
    }

    public String getIsbn() {
        return this.isbn;
    }

    private void setIsbn(String isbn) {
        if ("".equals(isbn) || isbn == null) {
            throw new IllegalArgumentException("ISBN of the book cannot be set to null or empty.");
        }
        this.isbn = isbn;
    }

    public Set<String> getTags() {
        return new HashSet<>(this.tags);
    }

    private void addTags(String... tags) {
        for (String tag : tags) {
            if ("".equals(tag) || tag == null) {
                throw new IllegalArgumentException("Tag cannot be null or empty.");
            }

            this.tags.add(tag);
        }
    }

    public Set<Author> getAuthors() {
        return new HashSet<>(this.authors);
    }

    private void addAuthors(Author... authors) {
        for (Author author : authors) {
            if (author == null) {
                throw new IllegalArgumentException("Author cannot be null.");
            }

            this.authors.add(author);
        }
    }
}
