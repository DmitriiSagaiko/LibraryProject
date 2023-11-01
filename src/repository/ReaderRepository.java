package repository;

import lib.MyArrayList;
import models.Book;
import models.Reader;

public class ReaderRepository {

  private final MyArrayList<Reader> readers;

  public ReaderRepository() {
    readers = new MyArrayList<>();
    init();
  }

  private void init() {
    readers.addAll(
        new Reader("Dmitrii", "Sagaiko", 29, "denegnet12345@mail.ru", "123456"),
        new Reader("Maxim", "Povernov", 42, "maxpover@gmail.com", "qwerty"),
        new Reader("Alex", "Ivanov", 18, "alex2005@yandex.ru", "zxcvb"));
  }

  public MyArrayList<Reader> getAll() {
    MyArrayList<Reader> output = new MyArrayList<>();
    if (readers.isEmpty()) {
      return null;
    }
    if (readers.size() == 1) {
      output.add(readers.get(0));
      return output;
    }
    for (Reader reader : readers) {
      output.add(reader);
    }
    return output;
  }

  public void addReader(Reader reader) {
    readers.add(reader);//
    System.out.println("Пользователь добавлен\n");
  }

  public MyArrayList<Book> getBooksByReader(Reader reader) {
    MyArrayList<Book> output =new MyArrayList<>();
    output=reader.getBooksOfReader();
    return output;
  }


}
