package models.task;

import models.user.User;

import java.util.Date;

import static java.lang.Math.round;

public class DatedTask extends Task {
  private Date dueDate; // CHANGER LA DATE UTILISER LocalDate

  // Constructeur pas obligatoire
  public DatedTask(String title, String description, boolean done, User creator, Date dueDate) {
    super(title, description, done, creator);
    this.dueDate = dueDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  @Override
  public String toString() {
    return String.format(
        "ID :  %d %n" +
            " DATED TASK %n" +
            " Titre : %s %n" +
            " Description : %s %n" +
            " Tâche faite : %b %n" +
            " Créateur de la tâche : %s" +
            " Date de création : %s",
        round(id), title, description, done, creator, dueDate
    );
  }
}
