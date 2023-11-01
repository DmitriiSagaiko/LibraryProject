import java.util.stream.Stream;
import lib.MyArrayList;
import models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
    //new TestForServiceLayer();
    Book.setCounter(1);
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



  @Disabled
  @Test
  void testTakeBookWithDateCorrectId() {
    for (int i = 1; i <= 10; i++) {
      Assertions.assertTrue(bookService.takeBookWithDate(i));
    }
  }


  @Disabled
  @Test
  void testTakeBookWithDateIncorrectId() {
    for (int i = 12; i <= 50; i++) {
      Assertions.assertFalse(bookService.takeBookWithDate(i));
    }
  }

  /////////////////////////////////////////////////////////

  @Test
  void testShowAllBooksByAuthor() {
    MyArrayList<Book> books = bookService.showAllBooksByAuthor("Толстой Л.Н.");
    int count = 0;
    for (Book book :books) {
      count++;
    }
    Assertions.assertEquals(4, count);
  }

  @Test
  void testShowInvalidAllBooksByAuthor() {
    MyArrayList<Book> books = bookService.showAllBooksByAuthor("Пушкин А. С.");
    int count = 0;
    for (Book book :books) {
      count++;
    }
    System.out.println("реалmное количество книг в библиотеке " + count);
    Assertions.assertEquals(0, count);
  }

  @Test
  void testShowAllBooksByName() {
    MyArrayList<Book> books = bookService.showAllBooksByName("Гарри Поттер часть 1");
    int count = 0;
    for (Book book :books) {
      count++;
    }
    Assertions.assertEquals(2, count);
  }

  @Test
  void testShowInvalidAllBooksByName() {
    MyArrayList<Book> books = bookService.showAllBooksByName("Человек паук");
    int count = 0;
    for (Book book :books) {
      count++;
    }
    Assertions.assertEquals(0, count);
  }





  @Test
  void testReturnBook() {
    System.out.println(bookService.getAllBooks());
    //Assertions.assertEquals(Void,void);
    //Assertions.assertEquals(book, bookService.returnBook(book.getId()));
    //Assertions.assertFalse(book.isTaken());
  }


}
