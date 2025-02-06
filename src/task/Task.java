package task;

import user.User;

import java.util.Objects;

import static java.lang.Math.round;

public class Task {

  private static int count;

  static {
    count = 0;
  }

  {
    count++;
  }

  protected final long id; // ID unique de chaque tâche (non statique)
  protected String title;
  protected String description;
  protected boolean done;
  protected User creator;

  // CONSTRUCTOR
  public Task(String title, String description, boolean done, User creator) {
    id = count; // Génère un ID unique à chaque création d'une tâche
    this.title = title;
    this.description = description;
    this.done = done;
    this.creator = creator;
  }

  // GETTERS AND SETTERS
  public double getId() {
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

  // TO STRING
  public String toString() {
    return String.format(
        "ID :  %d %n" +
            " Titre : %s %n" +
            " Description : %s %n" +
            " Tâche faite : %b %n" +
            " Créateur de la tâche : %s",
        round(id), title, description, done, creator
    );
  }

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
