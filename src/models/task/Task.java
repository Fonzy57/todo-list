package models.task;

import models.user.User;

import java.util.Objects;

import static java.lang.Math.round;

public class Task {

  protected Long id;
  protected String title;
  protected String description;
  protected boolean done;
  protected User creator;

  // CONSTRUCTOR
  public Task(String title, String description, boolean done, User creator) {
    this.title = title;
    this.description = description;
    this.done = done;
    this.creator = creator;
  }

  public Task(Long id, String title, String description, boolean done, User creator) {
    this(title, description, done, creator);
    this.id = id;
  }

  // GETTERS AND SETTERS
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", done=" + done +
        ", creator=" + creator +
        '}';
  }

  // TO STRING
//  public String toString() {
//    return String.format(
//        "ID :  %d %n" +
//            " Titre : %s %n" +
//            " Description : %s %n" +
//            " Tâche faite : %b %n" +
//            " Créateur de la tâche : %s",
//        id, title, description, done, creator
//    );
//  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Task task = (Task) o;
    return Double.compare(id, task.id) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
