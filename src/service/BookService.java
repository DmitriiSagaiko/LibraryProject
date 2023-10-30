package service;

import models.Book;
import repository.BookRepository;

public class BookService {

  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public boolean addBook(String name, String author, int publishDate, int numberOfPages) {
    //TODO Проверить есть ли такая книга уже в базе. Если книги нет - добавить

    Book book = new Book(name, author, publishDate, numberOfPages);

    bookRepository.addBook(book);
    return true;
  }

  public Book[] getAllBooks() {
    //TODO Через репозиторий обратиться к методу получения всех книг в массиве.

    Book[] output = bookRepository.getAll();

    return output;
  }

  public Book takeBookWithDate(int id) {
    // Если книга уже взята( isTaken == true), return null + собщение( нельзя взять взятое)
    //TODO У каждой книги свой ID, Книга будет браться по id.
    //TODO дата/время будет браться с помощью LocalDate
    // книге в поле isTaken присвоить true;

    Book book = bookRepository.takeBookWithDate(id);

    return book;
  }

  public Book returnBook(int id) {
    //TODO. Проверить через if ID, если такого id нет, то книгу нельзя вернуть.
    // Если такая книга есть, то вернуть ее через репозиторий

    // isTaken == false
    Book book = bookRepository.returnBook(id);
    return book;
  }

  public Book[] returnAllFreeBooks() {
    //TODO в репозитории пробежаться циклом по всем книгам,
    // Если isTaken==false, добавить ее в новый массив.
    // Важно возвращать копию массива, а не исходный вариант.

    Book[] output = bookRepository.returnAllFreeBooks();
    return output;
  }

  public Book[] returnAllTakenBooks() {
    //TODO в репозитории пробежаться циклом по всем книгам,
    // Если isTaken==true, добавить ее в новый массив.
    // Важно возвращать копию массива, а не исходный вариант.

    Book[] output = bookRepository.returnAllTakenBooks();
    return output;
  }

  public Book[] returnAllBooksByAuthor(String name) {
    // TODO Пробежаться циклом по всем книгам, если Author.isEqual name
    // Добавить его в массив
    // Важно возвращать копию массива, а не исходный вариант.

    Book[] output = bookRepository.returnAllBooksByAuthor(name);
    return output;
  }


  public Book[] returnBooksByName(String name) {
    // TODO Пробежаться циклом по всем книгам, если BookName.isEqual name
    // Добавить его в массив
    // Важно возвращать копию массива, а не исходный вариант.

    Book[] output = bookRepository.returnAllBooksByName(name);
    return output;

  }






}
