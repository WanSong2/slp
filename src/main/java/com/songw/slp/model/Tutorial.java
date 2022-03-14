package com.songw.slp.model;


public class Tutorial {
  private Long id;
  private String title;
  private String description;
  private Author author;

  public Tutorial() {
  }

  public Tutorial(String title, String description, Author author) {
    this.title = title;
    this.description = description;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", author=" + author + "]";
  }
}
