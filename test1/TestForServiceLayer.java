import models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import service.BookService;
public class TestForServiceLayer {

  private BookService bookService;

  private BookRepository bookRepository;


  public TestForServiceLayer() {
    this.bookRepository = new BookRepository();
    this.bookService = new BookService(bookRepository);
  }

  @BeforeEach
  void setUp() {
    new TestForServiceLayer();
  }

  @Test
  void testAddBook() {
    Book book1 = new Book("Война и Мир часть 1", "Толстой Л.Н.", 1915, 460); // существующая
    Book book2 = new Book("Война и Мир часть 1", "Толстой Л.Н.", 2300, 460); // Несуществующая

    Assertions.assertTrue(bookService.addBook("Война и Мир часть 1", "Толстой Л.Н.", 1915, 460));

  }


}
