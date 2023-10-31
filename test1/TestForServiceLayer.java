import models.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import service.BookService;
public class TestForServiceLayer {

  private BookService bookService;

  private BookRepository bookRepository;

  private Book book;

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

  }


}
