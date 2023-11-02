package view;

import java.util.Scanner;
import service.BookService;

public class Menu {

  private BookService bookService;

  public Menu(BookService bookService) {
    this.bookService = bookService;
  }

  public void run() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("1. Добавить книгу");
      System.out.println("2. Получить список всех книг в библиотеке");
      System.out.println("3. Взять книгу из библиотеки с текущей датой по id");
      System.out.println("4. Вернуть книгу в библиотеку по id");
      System.out.println("5. Показать все доступные книги");
      System.out.println("6. Показать все книги, которые были взяты");
      System.out.println("7. Показать все книги от заданного автора");
      System.out.println("8. Показать все книги по заданному названию");
      System.out.println("9. Зарегестрировать читателя");
      System.out.println("10. Авторизовать читателя");
      System.out.println("11. Показать все книги у текущего авторизованного пользователя");
      System.out.println("12. Выйти из пользователя");
      System.out.println("0. Выход из программмы");
      int command = scanner.nextInt();
      scanner.nextLine();

      switch (command) {
        case 0: {
          System.out.println("Выход из программы");
          System.exit(0);
        }
        break;
        case 1: {
          System.out.println("Введите название книги");
          String name = scanner.nextLine();
          System.out.println("Введите автора");
          String author = scanner.nextLine();
          System.out.println("введите год публикации");
          int year = scanner.nextInt();
          System.out.println("Введите количество страниц");
          int pages = scanner.nextInt();
          bookService.addBook(name, author, year, pages);
        }
        break;
        case 2: {
          System.out.println(bookService.getAllBooks());
        }

        break;
        case 3: {
          System.out.println("Введите id желаемой книги");
          int id = scanner.nextInt();
          bookService.takeBookWithDate(id, bookService.getAuthorisedReader());
        }
        break;
        case 4: {
          System.out.println("Введите id возвращаемой книги");
          int id = scanner.nextInt();
          bookService.returnBook(id);
        }
        break;
        case 5: {
          System.out.println(bookService.showAllFreeBooks());

        }
        break;
        case 6: {
          System.out.println(bookService.showAllTakenBooks());
        }
        break;
        case 7: {
          System.out.println("Введите искомого автора");
          String author = scanner.nextLine();
          System.out.println(bookService.showAllBooksByAuthor(author));
        }
        break;
        case 8: {
          System.out.println("Введите искомое название");
          String name = scanner.nextLine();
          System.out.println(bookService.showAllBooksByName(name));
        }
        break;
        case 9: {
          System.out.println("Введите Имя");
          String firstName = scanner.nextLine();
          System.out.println("Введите Фамилию");
          String lastName = scanner.nextLine();
          System.out.println("введите возраст");
          int age = scanner.nextInt();
          scanner.nextLine();
          System.out.println("Введите email");
          String email = scanner.nextLine();
          System.out.println("Введите пароль");
          String password = scanner.nextLine();
          System.out.println(
              "Пользователь зарегестрирован: " + bookService.registerReader(firstName, lastName,
                  age, email, password));
        }
        break;
        case 10: {
          if (bookService.getAuthorisedReader() != null) {
            System.out.println("Пользователь уже залогинен! Сначала выйдите из учетной записи!");
            break;
          }
          System.out.println("Введите email");
          String email = scanner.nextLine();
          System.out.println("Введите пароль");
          String password = scanner.nextLine();
          System.out.println(
              "Пользователь авторизован: " + bookService.authorizeReader(email, password));
        }
        break;
        case 11: {
          System.out.println(bookService.showAllbooksByReader());
        }
        break;
        case 12: {
          System.out.println(bookService.logoutReader());
        }
      }
    }
  }
}
