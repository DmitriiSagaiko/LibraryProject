package view;

import repository.BookRepository;
import repository.ReaderRepository;
import service.BookService;

public class Main {

  public static void main(String[] args) {
    BookRepository bookRepository = new BookRepository();
    ReaderRepository readerRepository = new ReaderRepository();
    BookService bookService = new BookService(bookRepository, readerRepository);
    Menu menu = new Menu(bookService);
    menu.run();
  }
}
