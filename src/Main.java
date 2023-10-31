import repository.BookRepository;
import service.BookService;

public class Main {

  public static void main(String[] args) {
    BookRepository bookRepository = new BookRepository();
    BookService bookService = new BookService(bookRepository);
    System.out.println(bookRepository.getAll());
    System.out.println(bookService.addBook("Новая книга", "Пелевин В.", 2004, 238));
    System.out.println(bookService.addBook("Новая книга", "Пелевин В.", 2004, 238));
    System.out.println(bookRepository.getAll());
    System.out.println(bookRepository.takeBookWithDate(1));
    System.out.println(bookRepository.takeBookWithDate(100));
    System.out.println(bookRepository.getAll());
  }
}
