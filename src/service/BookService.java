package service;

import java.time.LocalDate;
import lib.MyArrayList;
import models.Book;
import models.Reader;
import repository.BookRepository;
import repository.ReaderRepository;

public class BookService {

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;

  public BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
    this.bookRepository = bookRepository;
    this.readerRepository = readerRepository;
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

  public boolean takeBookWithDate(int id, Reader reader) {
    // Будет вызываться 2 метода про пользователя
    return bookRepository.takeBookWithDate(id, reader) != null;
  }

  public void returnBook(int id) {
    bookRepository.returnBook(id, getAuthorisedReader());
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

  public boolean registerReader(String firstName, String lastName, int age, String email,
      String password) {
    for (Reader reader : readerRepository.getAll()) {
      if (reader.getEmail().equals(email)) {
        System.out.println("Такой пользователь уже есть");
        return false;
      }
    }
    Reader reader = new Reader(firstName, lastName, age, email, password);
    readerRepository.addReader(reader);
    return true;
  }

  public boolean authorizeReader(String email, String password) {
    for (Reader reader : readerRepository.getAll()) {
      if (reader.getEmail().equals(email) && reader.getPassword().equals(password)) {
        System.out.println("Авторизация прошла успешно");
        reader.setAuthorized(true);
        return true;
      }
    }
    System.out.println("Невверно введен логин/пароль. Попробуйте еще раз");
    return false;
  }

  public MyArrayList<Book> showAllbooksByReader() {
    for (Reader reader : readerRepository.getAll()) {
      if (!reader.isAuthorized()) {
        continue;
      } else {
        System.out.println("У читателя следующие книги: \n");
        return readerRepository.getBooksByReader(reader);
      }
    }
    System.out.println("Нет текущего пользоваталея. Авторизуйтесь");
    return null;
  }

  public Reader getAuthorisedReader() {
    for (Reader reader : readerRepository.getAll()) {
      if (reader.isAuthorized()) {
        return reader;
      }
    }
    return null;
  }

  public boolean logoutReader() {
    if (getAuthorisedReader() == null) {
      System.out.println("Читатель не авторизован");
      return false;
    }

    getAuthorisedReader().setAuthorized(false);
    System.out.println("Выход выполнен");
    return true;
  }


}
