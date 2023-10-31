package models;

import java.time.LocalDate;

public class Book {
    private String name;

    private String Author;

    private int publishDate;

    private int numberOfPages;

    private boolean isTaken;

    private final int id;

    private static int counter = 1;

    private LocalDate localDate;

  public Book(String name, String author, int publishDate, int numberOfPages) {
    this.name = name;
    Author = author;
    this.publishDate = publishDate;
    this.numberOfPages = numberOfPages;
    this.id = counter;
    counter++;
    localDate = LocalDate.of(2000,11,15);
    System.out.println(counter + " Это наш counter");
  }

  @Override
  public String toString() {
    return "Book{" +
        "name='" + name + '\'' +
        ", Author='" + Author + '\'' +
        ", publishDate=" + publishDate +
        ", numberOfPages=" + numberOfPages +
        ", isTaken=" + isTaken +
        ", id=" + id +
        '}' + "\n";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return Author;
  }

  public void setAuthor(String author) {
    Author = author;
  }

  public int getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(int publishDate) {
    this.publishDate = publishDate;
  }

  public int getNumberOfPages() {
    return numberOfPages;
  }

  public void setNumberOfPages(int numberOfPages) {
    this.numberOfPages = numberOfPages;
  }

  public boolean isTaken() {
    return isTaken;
  }

  public void setTaken(boolean taken) {
    isTaken = taken;
  }

  public int getId() {
    return id;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }
}
