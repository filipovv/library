package app.services;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.enums.Status;
import app.entities.user.User;
import app.entities.history.History;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;

public class BookService {
    private BookRepository bookRepository;
    private HistoryRepository historyRepository;

    public BookService(BookRepository bookRepository, HistoryRepository historyRepository) {
        this.bookRepository = bookRepository;
        this.historyRepository = historyRepository;
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

    public String getDownloadLink(User user, Book book) {
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
                    History history = this.historyRepository.getHistoryByUser(user);
                    if (history == null) {
                        history = new History(user);
                        this.historyRepository.addUserHistory(history);
                    }

                    history.addToHistory(book, Status.DOWNLOADED);
                    break;
                }
            }
        }

        return result;
    }

    public String getOnlineLink(User user, Book book) {
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
                    History history = this.historyRepository.getHistoryByUser(user);
                    if (history == null) {
                        history = new History(user);
                        this.historyRepository.addUserHistory(history);
                    }

                    history.addToHistory(book, Status.VIEWED);
                    break;
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
