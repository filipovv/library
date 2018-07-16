package app.services;

import app.entities.book.Book;
import app.entities.book.EBook;
import app.entities.enums.Status;
import app.entities.user.User;
import app.entities.history.History;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;

/**
 * BookService class is used to provide basic book functionality not related to the book borrowing process.
 */
public class BookService {
    private BookRepository bookRepository;
    private HistoryRepository historyRepository;

    /**
     * Constructor for the Book Service
     *
     * @param bookRepository    The repository for the books
     * @param historyRepository The repository for the history
     */
    public BookService(BookRepository bookRepository, HistoryRepository historyRepository) {
        this.bookRepository = bookRepository;
        this.historyRepository = historyRepository;
    }

    /**
     * Method used to validate an Ebook entry by:
     * if it's not null;
     * if it's an instance of EBook;
     * if it's contained in the repository
     *
     * @param book Object of type Book to be validated
     * @return Boolean value representing if the book is valid or not
     */
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

    /**
     * Method used to get a download link from the eBook for the user to download.
     * Adds a VIEWED history entry for the user.
     *
     * @param user Object of type User serving as the requesting user
     * @param book Object of type Book for the download link to be extracted from
     * @return String value representing the download link of the eBook
     */
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

    /**
     * Method used to get the read-online link from the eBook for the user to view.
     * Adds a DOWNLOADED history entry for the user.
     *
     * @param user Object of type User serving as the requesting user.
     * @param book Object of type Book for the read-online link to be extracted from
     * @return String value representing the read-online link of the eBook
     */
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

    /**
     * Method used to get a certain book information.
     *
     * @param book Object of type Book to get the info from
     * @return String value representing a summed up information about the given book
     */
    public String getBookInfo(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null to get info for it.");
        }

        // TODO: search in repository instead.
        return book.toString();
    }
}
