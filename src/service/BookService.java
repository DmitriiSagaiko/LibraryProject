package service;

import java.time.LocalDate;
import lib.MyArrayList;
import models.Book;
import repository.BookRepository;

public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public boolean addBook(String name, String author, int publishDate, int numberOfPages) {
    for (Book book : bookRepository.getAll()) {
      if (book.getAuthor().equals(author) && book.getName().equals(name)
          && book.getPublishDate() == publishDate && book.getNumberOfPages() == numberOfPages) {
        System.out.println("Такая книга уже в библиотеке есть");
        return false;
      }
    }
    Book book = new Book(name, author, publishDate, numberOfPages);
    bookRepository.addBook(book);
    return true;
  }

  public MyArrayList<Book> getAllBooks() {
    return bookRepository.getAll();
  }

  public boolean takeBookWithDate(int id) {
    // Будет вызываться 2 метода про пользователя
    return bookRepository.takeBookWithDate(id) != null;
  }

  public void returnBook(int id) {
    bookRepository.returnBook(id);
  }


  public MyArrayList<Book> showAllFreeBooks() {
    return bookRepository.getBooksByPredicate(book -> !book.isTaken());
  }


  public MyArrayList<Book> showAllTakenBooks() {
    return bookRepository.getBooksByPredicate(Book::isTaken);
  }

  public MyArrayList<Book> showAllBooksByAuthor(String name) {
    return bookRepository.getBooksByPredicate(book -> book.getAuthor().equals(name));
  }


  public MyArrayList<Book> showAllBooksByName(String name) {
    return bookRepository.getBooksByPredicate(book -> book.getName().equals(name));
  }

}
