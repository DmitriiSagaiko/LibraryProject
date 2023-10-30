package repository;

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
        new Book("Война и Мир", "Толстой Л.Н.", 1915, 1815)


    );
  }

  public void addBook(Book book) {
    books.add(book);//
  }
}
