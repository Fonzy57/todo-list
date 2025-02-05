package task;

import java.util.Date;

public class DatedTask extends Task {
  private Date dueDate; // CHANGER LA DATE UTILISER LocalDate

  public DatedTask(String title, String description, boolean done, double creator, Date dueDate) {
    super(title, description, done, creator);
    this.dueDate = dueDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
}
