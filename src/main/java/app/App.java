package app;

import app.entities.book.Author;
import app.entities.book.Book;
import app.entities.book.EBook;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Set<String> tags = new HashSet<>();
        tags.add("cat");
        tags.add("dog");

        Author author = new Author.AuthorBuilder("Tolkien", "Belgium", LocalDate.of(1865, 3, 16)).build();
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        Book book = new EBook.EBookBuilder("LotR", "Fiction", "9348603458", tags, authors, "hgksdhgs", "figkshg").build();

        System.out.printf("Book name: %s%nGenre: %s%nISBN: %s%nDownload link: %s%n", book.getTitle(), book.getGenre(), book.getIsbn(), ((EBook) book).getDownloadLink());
    }
}
