package repository;

import java.time.LocalDate;
import lib.MyArrayList;
import models.Book;

public class BookRepository {

  private final MyArrayList<Book> books;

  private final static int MAX_BOOK_COUNTER = 100;

  public BookRepository() {
    books = new MyArrayList<>();
    init();
  }

  private void init() {
    //TODO залить 8-10 книг
    books.addAll(
        // TODO добавить несколько кнги от одного автора
        new Book("Война и Мир часть 1", "Толстой Л.Н.", 1915, 460),
        new Book("Война и Мир часть 2", "Толстой Л.Н.", 1917, 460),
        new Book("Война и Мир часть 3", "Толстой Л.Н.", 1918, 460),
        new Book("Война и Мир часть 4", "Толстой Л.Н.", 1920, 460),
        new Book("Гарри Поттер часть 1", "Роулинг Дж.", 1950, 600),
        new Book("Горе от ума", "Грибоедов А.С.", 1905, 250),
        new Book("Алмазная колесница", "Акунин Б.", 1988, 340),
        new Book("Сборник детективов", "Донцова Д.", 2004, 237),
        new Book("Новая книга", "Пелевин В.", 2004, 237));
  }

  public void addBook(Book book) {
    books.add(book);//
  }

  public MyArrayList<Book> getAll() {
    MyArrayList<Book> output = new MyArrayList<>();
    if (books.isEmpty()) {
      return null;
    }
    if (books.size() == 1) {
      output.add(books.get(0));
      return output;
    }
    for (Book book : books) {
      output.add(book);
    }
    return output;
  }

  public Book takeBookWithDate(int id) {
    for (Book book : books) {
      if (id == book.getId()) {
        if (!book.isTaken()) {
          book.setTaken(true);
          book.setLocalDate(LocalDate.now());
          System.out.println(book.getName() + " успешно взята");
          return book;
        } else {
          System.out.println("Книга уже взята! Попобуйте взять другую книгу");
          return null;
        }
      } else {
        System.out.println("Такой книги в библиотеке нет! Попробуйте взять другую книгу");
        return null;
      }
    }
    return null;
  }
}
