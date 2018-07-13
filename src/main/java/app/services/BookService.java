package app.services;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.repositories.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private boolean validateEBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null.");
        }

        boolean flag = true;
        if (!(book instanceof EBook)) {
            flag = false;
        }

        if (!this.bookRepository.getBooks().contains(book)) {
            flag = false;
        }

        return flag;
    }

    public String getDownloadLink(Book book) {
        if (!this.validateEBook(book)) {
            throw new IllegalArgumentException("EBook validation failed.");
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
        if (!this.validateEBook(book)) {
            throw new IllegalArgumentException("EBook validation failed.");
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
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null to get info for it.");
        }

        return book.toString();
    }
}
