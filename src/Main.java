import repository.BookRepository;
import service.BookService;

public class Main {

  public static void main(String[] args) {
    BookRepository bookRepository = new BookRepository();
    BookService bookService = new BookService(bookRepository);
  }
}
