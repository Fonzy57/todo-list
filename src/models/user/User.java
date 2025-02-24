package models.user;

import static java.lang.Math.round;

public class User {

  private Long id;
  private String firstName;
  private String lastName;

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(Long id, String firstName, String lastName) {
    this(firstName, lastName);
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
        "USER : " +
            " ID: %d" +
            " First Name: %s," +
            " Last Name: %s%n", round(id), firstName, lastName
    );
  }
}
