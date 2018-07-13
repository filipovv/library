package app.services;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.repositories.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getDownloadLink(Book book) {
        if (!(book instanceof EBook)) {
            throw new IllegalArgumentException("Cannot return read-online link for non-electronic books.");
        }

        String result = null;
        if (!this.bookRepository.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book does not exist in the repository.");
        } else {
            for (Book entry : this.bookRepository.getBooks()) {
                if (entry.equals(book)) {
                    if (((EBook) entry).getDownloadLink() == null) {
                        throw new IllegalArgumentException("Book does not have a download link available.");
                    }
                    result = ((EBook) entry).getDownloadLink();
                }
            }
        }

        return result;
    }

    public String getOnlineLink(Book book) {
        if (!(book instanceof EBook)) {
            throw new IllegalArgumentException("Cannot return read-online link for non-electronic books.");
        }

        String result = null;
        if (!this.bookRepository.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book does not exist in the repository.");
        } else {
            for (Book entry : this.bookRepository.getBooks()) {
                if (entry.equals(book)) {
                    result = ((EBook) entry).getReadOnlineLink();
                }
            }
        }

        return result;
    }

    public String getBookInfo(Book book) {
        return book.toString();
    }
}
