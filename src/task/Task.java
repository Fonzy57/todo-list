package task;

import static java.lang.Math.round;

public class Task {

  private double id;
  protected String title;
  protected String description;
  protected boolean done;
  protected double creator;

  // CONSTRUCTOR
  public Task (double id, String title, String description, boolean done, double creator) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.done = done;
    this.creator = creator;
  }

  // GETTERS AND SETTERS
  public double getId () {
    return id;
  }

  public void setId (double id) {
    this.id = id;
  }

  public String getTitle () {
    return title;
  }

  public void setTitle (String title) {
    this.title = title;
  }

  public String getDescription () {
    return description;
  }

  public void setDescription (String description) {
    this.description = description;
  }

  public boolean isDone () {
    return done;
  }

  public void setDone (boolean done) {
    this.done = done;
  }

  public double getCreator () {
    return creator;
  }

  public void setCreator (double creator) {
    this.creator = creator;
  }

  // TO STRING
  public String toString () {
    return String.format(
        "Tâche avec l'id %d %n" +
            " Titre : %s %n" +
            " Description : %s %n" +
            " Tâche faite : %b %n" +
            " Créateur de la tâche : %d",
        round(id), title, description, done, round(creator)
    );
  }
}
