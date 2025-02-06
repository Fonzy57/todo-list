package user;

import static java.lang.Math.round;

public class User {
  private static int count;

  static {
    count = 0;
  }

  {
    count++;
  }

  private final long id; // ID unique de chaque tâche (non statique)
  private String firstName;

  public User(String firstName) {
    id = count; // Génère un ID unique à chaque création d'une tâche
    this.firstName = firstName;
  }

  // GETTERS AND SETTERS
  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  // TO STRING
  public String toString() {
    return String.format(
        "USER : %n" +
            " ID: %d%n" +
            " First Name: %s%n", round(id), firstName
    );
  }
}
