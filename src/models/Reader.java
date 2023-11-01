package models;

import lib.MyArrayList;

public class Reader {

  private String firstName;
  private String lastName;

  private String email; // наш уникальный ID
  private String password;

  private boolean isAuthorized;

  private int age;

  private MyArrayList<Book>booksOfReader;


  private int booksCount;

  //private static int count;

  public Reader(String firstName, String lastName, int age, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.email = email;
    this.password = password;
    this.booksOfReader = new MyArrayList<>();
    this.isAuthorized = false;
  }

  @Override
  public String toString() {
    return "\nReader{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        ", booksCount=" + booksCount +
        '}';
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getBooksCount() {
    return booksCount;
  }

  public void setBooksCount(int booksCount) {
    this.booksCount = booksCount;
  }

  public MyArrayList<Book> getBooksOfReader() {
    return booksOfReader;
  }

  public void addBooksOfReader(Book book) {
    this.booksOfReader.add(book);
    booksCount++;
  }

  public void deleteBooksOfReader(Book book) {
    this.booksOfReader.remove(book);
    booksCount--;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isAuthorized() {
    return isAuthorized;
  }

  public void setAuthorized(boolean authorized) {
    isAuthorized = authorized;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
