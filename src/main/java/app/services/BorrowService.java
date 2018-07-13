package app.services;

import app.entities.book.Book;
import app.entities.book.BorrowQueue;
import app.entities.book.PaperBook;
import app.entities.user.BorrowBookEntry;
import app.entities.user.User;
import app.entities.user.UserHistory;
import app.repositories.BookRepository;
import app.repositories.HistoryRepository;
import app.repositories.QueueRepository;

public class BorrowService {
    private BookRepository bookRepository;
    private HistoryRepository historyRepository;
    private QueueRepository queueRepository;

    public BorrowService(BookRepository bookRepository, HistoryRepository historyRepository, QueueRepository queueRepository) {
        this.bookRepository = bookRepository;
        this.historyRepository = historyRepository;
        this.queueRepository = queueRepository;
    }

    public void postponeReturn(User user, Book book, int days) {
        if (!this.validatePaperBook(book)) {
            throw new IllegalArgumentException("Paper book validation failed.");
        }

        for (UserHistory userHistory : this.historyRepository.getHistorySet()) {
            if (userHistory.getUser().equals(user)) {
                BorrowBookEntry entry = userHistory.findBorrowEntryByBook(book);
                if (entry == null) {
                    throw new IllegalArgumentException("Borrow entry for that book and user does not exist.");
                }
                entry.postpone(days);
                break;
            }
        }
    }

    private boolean validatePaperBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book must not be null.");
        }

        boolean flag = true;
        if (!(book instanceof PaperBook)) {
            flag = false;
        }

        if (!this.bookRepository.getBooks().contains(book)) {
            flag = false;
        }

        return flag;
    }

    public boolean isQueueLocked(Book book) {
        if (!this.validatePaperBook(book)) {
            throw new IllegalArgumentException("Paper book validation failed.");
        }

        return this.queueRepository.isQueueLocked(book);
    }

    public void waitInQueue(User user, Book book) {
        if (!this.validatePaperBook(book)) {
            throw new IllegalArgumentException("Paper book validation failed.");
        }

        BorrowQueue queue = this.queueRepository.findQueueByBook(book);
        if (queue != null) {
            if (queue.getUsers().contains(user)) {
                throw new IllegalArgumentException("User is already in this queue.");
            }

            queue.addUser(user);
        } else {
            queue = new BorrowQueue((PaperBook) book);
            queue.addUser(user);
            this.queueRepository.getQueues().add(queue);
        }
    }

    // TODO: Check if a queue for this book exists and if it does - notify next user in line that book is available for borrow.

    public String notifyBorrowAvailable(BorrowQueue borrowQueue) {
        return String.format("The book %s is available for borrow until %s!%n",
                borrowQueue.getPaperBook().getTitle(), borrowQueue.getPickBookDeadline());
    }

    public void returnBook(User user, Book book) {
        if (!this.validatePaperBook(book)) {
            throw new IllegalArgumentException("Paper book validation failed.");
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
                        BorrowQueue queue = this.queueRepository.findQueueByBook(book);
                        if (queue != null) {
                            User nextInQueue = queue.nextUser();
                            if (nextInQueue != null) {
                                this.notifyBorrowAvailable(queue);
                            }
                        }

                        break USER_LOOP;
                    }
                }
            }
        }
    }

    public void borrowBook(User user, Book book) {
        if (!this.validatePaperBook(book)) {
            throw new IllegalArgumentException("Paper book validation failed.");
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
