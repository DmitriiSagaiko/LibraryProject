import lib.MyArrayList;
import models.Book;
import models.Reader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.ReaderRepository;
import service.BookService;

public class TestForServiceLayer {
  private BookService bookService;
  private Book book;


  public TestForServiceLayer() {
    BookRepository bookRepository = new BookRepository();
    ReaderRepository readerRepository = new ReaderRepository();
    this.bookService = new BookService(bookRepository, readerRepository);
  }

  @BeforeEach
  void setUp() {
    Book.setCounter(1);
    bookService.logoutReader();
  }

  @Test
  void testAddExistingBook() {
    //("Война и Мир часть 1", "Толстой Л.Н.", 1915, 460); // существующая - ожидаю false
    Assertions.assertFalse(bookService.addBook("Война и Мир часть 1", "Толстой Л.Н.", 1915, 460));

  }

  @Test
  void testAddNonExistingBook() {
    //("Война и Мир часть 1", "Толстой Л.Н.", 2300, 460); // Несуществующая - ожидаю true
    Assertions.assertTrue(bookService.addBook("Война и Мир часть 1", "Толстой Л.Н.", 2300, 460));
  }

  @Test
  void testGetAll() {
    MyArrayList<Book> testArray = bookService.getAllBooks();
    int count = 0;
    for (Book book : testArray) {
      count++;
    }
    Assertions.assertEquals(10, count);
    bookService.addBook("Война и Мир часть 1", "Толстой Л.Н.", 2300, 460);
    Assertions.assertEquals(11, count + 1);
    bookService.addBook("Война и Мир часть 6", "Толстой Л.Н.", 2000, 4880);
    Assertions.assertEquals(12, count + 2);
  }


  /////////// Тестируем всевозможные взятия книги
  @Test
  void testTakeBookWithDateCorrectIdCorrectReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    for (int i = 1; i <= 10; i++) {
      Assertions.assertTrue(bookService.takeBookWithDate(i, reader));
    }
  }

  @Test
  void testTakeBookWithoutReader() {
    Reader reader = null;
    Assertions.assertFalse(bookService.takeBookWithDate(1, reader));
  }


  @Test
  void testTakeBookWithDateIncorrectIdCorrectReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    for (int i = 12; i <= 50; i++) {
      Assertions.assertFalse(bookService.takeBookWithDate(i, reader));
    }
  }

  @Test
  void takeTakenBookWithCorrectIdWithCorrectReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    Assertions.assertTrue(bookService.takeBookWithDate(1, reader));
    Assertions.assertFalse(bookService.takeBookWithDate(1, reader));
  }

  @Test
  void takeTheSameBookWithDifferentReaders() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    Assertions.assertTrue(bookService.takeBookWithDate(1, reader));
    bookService.logoutReader();
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    reader = bookService.getAuthorisedReader();
    Assertions.assertFalse(bookService.takeBookWithDate(1, reader));
  }

  @Test
  void takeTheDifferentBookWithDifferentReaders() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    Assertions.assertTrue(bookService.takeBookWithDate(1, reader));
    bookService.logoutReader();
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    reader = bookService.getAuthorisedReader();
    Assertions.assertTrue(bookService.takeBookWithDate(2, reader));
  }

  /////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////

  // Тестируем другие методы, связанные с книгами/авторами и тд

  @Test
  void testShowAllBooksByAuthor() {
    MyArrayList<Book> books = bookService.showAllBooksByAuthor("Толстой Л.Н.");
    int count = 0;
    for (Book book : books) {
      count++;
    }
    Assertions.assertEquals(4, count);
  }

  @Test
  void testShowInvalidAllBooksByAuthor() {
    MyArrayList<Book> books = bookService.showAllBooksByAuthor("Пушкин А. С.");
    int count = 0;
    for (Book book : books) {
      count++;
    }
    System.out.println("реалmное количество книг в библиотеке " + count);
    Assertions.assertEquals(0, count);
  }

  @Test
  void testShowAllBooksByName() {
    MyArrayList<Book> books = bookService.showAllBooksByName("Гарри Поттер часть 1");
    int count = 0;
    for (Book book : books) {
      count++;
    }
    Assertions.assertEquals(2, count);
  }

  @Test
  void testShowInvalidAllBooksByName() {
    MyArrayList<Book> books = bookService.showAllBooksByName("Человек паук");
    int count = 0;
    for (Book book : books) {
      count++;
    }
    Assertions.assertEquals(0, count);
  }

  /////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////

  // Тестируем все возможный возврат книг
  // 1. Возврат невзятой книги без пользователя. Ожидаю false
  // 2. Возврат книги без пользователя вне диапазона. Ожидаю false
  // 3. Возврат невзятой книги с пользователем. Ожидаю false
  // 4. Возврат книги с пользователем вне диапазона. Ожидаю false
  // 5. Возврат взятой книги. Ожидаю True
  // 6. Возврат взятой книги другим пользователем. Ожидаю false
  // 7. Возврат взятой книги одним пользователем 2 раза. Ожидаю false
  @Test
  void testReturnExistNottakenBookWithoutReader() {
    Assertions.assertFalse(bookService.returnBook(1));
  }

  @Test
  void testReturnNonExistNottakenBookWithoutReader() {
    Assertions.assertFalse(bookService.returnBook(50));
  }

  @Test
  void testReturnExistNottakenBookWithReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    Assertions.assertFalse(bookService.returnBook(1));
  }

  @Test
  void testReturnNonExistNottakenBookWithReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    Assertions.assertFalse(bookService.returnBook(50));
  }

  @Test
  void testReturnExistTakenBookWithReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(1, reader);
    Assertions.assertTrue(bookService.returnBook(1));
  }

  @Test
  void testReturnExistTakenBookWithAnotherReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(1, reader);
    bookService.logoutReader();
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    Assertions.assertFalse(bookService.returnBook(1));
  }

  @Test
  void testReturnExistTakenBookWithReaderTwoTimes() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(1, reader);
    bookService.returnBook(1);
    Assertions.assertFalse(bookService.returnBook(1));
  }

  //////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////
  // оставшиеся методы
  @Test
  void testValidRegiesterReader() {
    boolean valid = bookService.registerReader("Антон", "Пальчиков", 15, "anton228@yahoo.uk",
        "deltoplan");

    Assertions.assertTrue(valid);
  }

  @Test
  void testInvalidRegiesterReader() {
    boolean invalid = bookService.registerReader("Антон", "Пальчиков", 15, "maxpover@gmail.com",
        "deltoplan");
    Assertions.assertFalse(invalid);
  }

  @Test
  void testAuthorizeValidReader() {
    boolean valid = bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Assertions.assertTrue(valid);
  }

  @Test
  void testAuthorizeInvalidReader() {
    boolean invalid = bookService.authorizeReader("alex20051@yandex.ru", "zxcvb");
    Assertions.assertFalse(invalid);
    invalid = bookService.authorizeReader("alex2005@yandex.ru", "zxcvbn");
    Assertions.assertFalse(invalid);
  }

  ////////////////////////////////////////////////////////

  @Test
  void testShowAllBooksByZeroReader() {
    Assertions.assertNull(bookService.showAllbooksByReader());
  }

  @Test
  void testShowallBooksByReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(1, reader);
    Assertions.assertEquals(1, bookService.showAllbooksByReader().size());
    System.out.println(bookService.showAllbooksByReader());
    bookService.takeBookWithDate(2, reader);
    Assertions.assertEquals(2, bookService.showAllbooksByReader().size());
    System.out.println(bookService.showAllbooksByReader());
  }

  @Test
  void testShowallBooksByDifferentReader() {
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    Reader reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(1, reader);
    bookService.takeBookWithDate(2, reader);
    bookService.logoutReader();

    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    reader = bookService.getAuthorisedReader();
    bookService.takeBookWithDate(3, reader);
    bookService.takeBookWithDate(4, reader);
    Assertions.assertEquals(2, bookService.showAllbooksByReader().size());
    System.out.println(bookService.showAllbooksByReader());
  }
//////////////////////////////////////////////////////////////

  @Test
  void testAuthorizedReaderNull() {
    bookService.logoutReader();
    Assertions.assertNull(bookService.getAuthorisedReader());
  }

  @Test
  void testAuthorizedReaderNotNull() {
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    Reader reader = bookService.getAuthorisedReader();
    String a = "Maxim";
    String b = "Povernov";
    int c = 42;
    String d = "maxpover@gmail.com";
    String e = "qwerty";
    Assertions.assertEquals(a, reader.getFirstName());
    Assertions.assertEquals(b, reader.getLastName());
    Assertions.assertEquals(c, reader.getAge());
    Assertions.assertEquals(d, reader.getEmail());
    Assertions.assertEquals(e, reader.getPassword());
  }

  //////////////////////////////////////////////////

  @Test
  void testLogoutNull() {
    Assertions.assertFalse(bookService.logoutReader());
  }

  @Test
  void testLogoutNotNull() {
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    Assertions.assertTrue(bookService.logoutReader());
  }

  //////////////////////////////////


  @Test
  void testShowAllFreeBooks() {
    Assertions.assertEquals(10, bookService.showAllFreeBooks().size());
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    bookService.takeBookWithDate(1, bookService.getAuthorisedReader());
    bookService.takeBookWithDate(2, bookService.getAuthorisedReader());
    Assertions.assertEquals(8, bookService.showAllFreeBooks().size());
    bookService.returnBook(1);
    bookService.returnBook(2);
    Assertions.assertEquals(10, bookService.showAllFreeBooks().size());

    bookService.logoutReader();
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    bookService.takeBookWithDate(1, bookService.getAuthorisedReader());
    bookService.takeBookWithDate(2, bookService.getAuthorisedReader());
    Assertions.assertEquals(8, bookService.showAllFreeBooks().size());
  }

  @Test
  void testShowAllFreeBooksWithAllBooks() {
    testTakeBookWithDateCorrectIdCorrectReader();
    Assertions.assertEquals(0, bookService.showAllFreeBooks().size());
  }

  @Test
  void testShowAllTakenBooks() {
    Assertions.assertEquals(0, bookService.showAllTakenBooks().size());
    bookService.authorizeReader("maxpover@gmail.com", "qwerty");
    bookService.takeBookWithDate(1, bookService.getAuthorisedReader());
    bookService.takeBookWithDate(2, bookService.getAuthorisedReader());
    Assertions.assertEquals(2, bookService.showAllTakenBooks().size());
    bookService.returnBook(1);
    bookService.returnBook(2);
    Assertions.assertEquals(0, bookService.showAllTakenBooks().size());

    bookService.logoutReader();
    bookService.authorizeReader("alex2005@yandex.ru", "zxcvb");
    bookService.takeBookWithDate(1, bookService.getAuthorisedReader());
    bookService.takeBookWithDate(2, bookService.getAuthorisedReader());
    Assertions.assertEquals(2, bookService.showAllTakenBooks().size());
  }

  @Test
  void testShowAllTakenBooksWithAllBooks() {
    testTakeBookWithDateCorrectIdCorrectReader();
    Assertions.assertEquals(10, bookService.showAllTakenBooks().size());
  }

}
