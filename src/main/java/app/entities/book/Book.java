package app.entities.book;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Base class Book used to provide basic functionality and properties to all classes that inherit it.
 */
public abstract class Book {
    private String title;
    private String genre;
    private String summary;
    private String isbn;
    private Set<String> tags;
    private Set<Author> authors;

    /**
     * Costructor for base class Book
     *
     * @param title   The title of the book
     * @param genre   The genre of the book
     * @param summary A short summery for the book
     * @param isbn    ISBN of the book
     */
    protected Book(String title, String genre, String summary, String isbn) {
        this.setTitle(title);
        this.setGenre(genre);
        this.setSummary(summary);
        this.setIsbn(isbn);
        this.tags = new HashSet<>();
        this.authors = new HashSet<>();
    }

    /**
     * Method meant to be overwritten by the child classes. Used to receive the type of the book.
     *
     * @return String value, representing the book type.
     */
    protected abstract String getBookType();

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

    private void setSummary(String summary) {
        if ("".equals(summary) || summary == null) {
            throw new IllegalArgumentException("Summary of the book cannot be set to null or empty.");
        }
        this.summary = summary;
    }

    String getIsbn() {
        return this.isbn;
    }

    private void setIsbn(String isbn) {
        if ("".equals(isbn) || isbn == null) {
            throw new IllegalArgumentException("ISBN of the book cannot be set to null or empty.");
        }
        this.isbn = isbn;
    }

    public Set<String> getTags() {
        return this.tags;
    }

    public void addTags(String... tags) {
        for (String tag : tags) {
            if ("".equals(tag) || tag == null) {
                throw new IllegalArgumentException("Tag cannot be null or empty.");
            }

            this.tags.add(tag);
        }
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void addAuthors(Author... authors) {
        for (Author author : authors) {
            if (author == null) {
                throw new IllegalArgumentException("Author cannot be null.");
            }

            this.authors.add(author);
        }
    }
}
