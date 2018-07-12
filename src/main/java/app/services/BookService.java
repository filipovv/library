package app.services;

import app.entities.book.EBook;
import app.entities.book.PaperBook;
import app.repositories.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String getEBookInfo(EBook eBook) {
        return eBook.toString();
    }

    public String getPaperBookInfo(PaperBook paperBook) {
        return paperBook.toString();
    }
}
