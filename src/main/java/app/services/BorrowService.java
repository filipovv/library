package app.services;

import app.entities.book.Book;
import app.entities.book.PaperBook;
import app.entities.user.BorrowBookEntry;
import app.entities.user.User;
import app.entities.user.UserHistory;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;
import app.repositories.UserRepository;

public class BorrowService {
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private HistoryRepository historyRepository;

    public BorrowService(BookRepository bookRepository, UserRepository userRepository, HistoryRepository historyRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
    }

    public void returnBook(User user, Book book) {
        if (!(book instanceof PaperBook)) {
            throw new IllegalArgumentException("Cannot return a non Paper-Book copy.");
        }

        if (!this.bookRepository.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book not present in the repository.");
        }

        USER_LOOP:
        for (UserHistory userHistory : historyRepository.getHistorySet()) {
            if (userHistory.getUser().equals(user)) {
                if (userHistory.findBorrowEntryByBook(book) == null) {
                    throw new IllegalArgumentException("No borrow entry with that book exists.");
                }
                for (BorrowBookEntry borrowBookEntry : userHistory.getCurrentlyBorrowed()) {
                    if (borrowBookEntry.getBook().equals(book)) {
                        userHistory.returnBook(book);
                        break USER_LOOP;
                    }
                }
            }
        }
    }

    public void borrowBook(User user, Book book) {
        if (!(book instanceof PaperBook)) {
            throw new IllegalArgumentException("Cannot borrow a non Paper-Book copy.");
        }

        if (!this.bookRepository.getBooks().contains(book)) {
            throw new IllegalArgumentException("Book not present in the repository.");
        }

        BOOK_LOOP:
        for (Book bookEntry : this.bookRepository.getBooks()) {
            if (bookEntry.equals(book)) {
                if (((PaperBook) bookEntry).availableCopies() == 0) {
                    throw new IllegalArgumentException("No copies available to borrow at the moment.");
                }
                for (UserHistory userHistory : historyRepository.getHistorySet()) {
                    if (userHistory.getUser().equals(user)) {
                        for (BorrowBookEntry borrowBookEntry : userHistory.getCurrentlyBorrowed()) {
                            if (borrowBookEntry.getBook().equals(book)) {
                                throw new IllegalArgumentException("Book already borrowed.");
                            }
                        }
                        userHistory.borrowBook(bookEntry);
                        ((PaperBook) bookEntry).borrowCopy();
                        break BOOK_LOOP;
                    }
                }
                UserHistory userHistory = new UserHistory(user);
                userHistory.borrowBook(book);
                historyRepository.addUserHistory(userHistory);
                break;
            }
        }
    }
}
