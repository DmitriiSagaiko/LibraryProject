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
    MyArrayList<Book> output = bookRepository.getAll();
    return output;
  }

  public boolean takeBookWithDate(int id) {
    //bookRepository.takeBookWithDate(id);
    return bookRepository.takeBookWithDate(id) != null;
  }

  public void returnBook(int id) {
    bookRepository.returnBook(id);
  }

  public MyArrayList<Book> showAllFreeBooks() {
    //TODO в репозитории пробежаться циклом по всем книгам,
    // Если isTaken==false, добавить ее в новый массив.
    // Важно возвращать копию массива, а не исходный вариант.

    //Book[] output = bookRepository.showAllFreeBooks();
    //return output;
    return null;
  }

  public MyArrayList<Book> showAllTakenBooks() {
    //TODO в репозитории пробежаться циклом по всем книгам,
    // Если isTaken==true, добавить ее в новый массив.
    // Важно возвращать копию массива, а не исходный вариант.

    //Book[] output = bookRepository.showAllTakenBooks();
    //return output;
    return null;
  }

  public MyArrayList<Book> showAllBooksByAuthor(String name) {
    MyArrayList<Book> output = bookRepository.showAllBooksByAuthor(name);
    return output;
    //return null;
  }

  public Book[] showBooksByName(String name) {
    // TODO Пробежаться циклом по всем книгам, если BookName.isEqual name
    // Добавить его в массив
    // Важно возвращать копию массива, а не исходный вариант.

    //Book[] output = bookRepository.showAllBooksByName(name);
    //return output;
    return null;

  }


}
