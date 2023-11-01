package models;

public class Reader {

  private String firstName;
  private String lastName;

  private int age;
  //Список книг


  private int booksCount;

  public Reader(String firstName, String lastName, int age) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Reader{" +
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
}
