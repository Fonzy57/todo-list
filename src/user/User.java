import static java.lang.Math.round;

public class User {
  private static double idCounter = 0; // Compteur global pour générer des IDs uniques
  private final double id; // ID unique de chaque tâche (non statique)
  private String firstName;

  public User(String firstName) {
    id = generateId(); // Génère un ID unique à chaque création d'une tâche
    this.firstName = firstName;
  }

  // ID INCREMENT
  public static double generateId() {
    return idCounter++; // Incrémente le compteur global
  }

  // GETTERS AND SETTERS
  public double getId() {
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
