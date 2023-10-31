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
import service.BookService;

public class TestForServiceLayer {

  private BookService bookService;

  //private BookRepository bookRepository;


  public TestForServiceLayer() {
    BookRepository repository = new BookRepository();
    System.out.println("создали еще один репозиторий");
    this.bookService = new BookService(repository);
  }

  @BeforeEach
  void setUp() {
    //new TestForServiceLayer();
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

  @ParameterizedTest
  //@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
  @MethodSource("testdata")
  void testTakeBookWithDateCorrectId(int correctId) {
    System.out.println(correctId);
    System.out.println(bookService.getAllBooks());
    Assertions.assertTrue(bookService.takeBookWithDate(correctId));
  }
  static Stream<Integer> testdata() {
    return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  }

  @Test
  void testTakeBookWithDateCorrectId1(){
    for (int i = 1; i<=10; i++ )
    Assertions.assertTrue(bookService.takeBookWithDate(i));
  }

  @Test
  void test() {
    Assertions.assertTrue(bookService.takeBookWithDate(2));
  }

  @Test
  void test1() {
    Assertions.assertTrue(bookService.takeBookWithDate(3));
  }


}
