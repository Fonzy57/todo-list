public class User {
  private double id;
  private String firstName;

  public User (double id, String firstName) {
    this.id = id;
    this.firstName = firstName;
  }

  // GETTERS AND SETTERS
  public double getId () {
    return id;
  }

  public void setId (double id) {
    this.id = id;
  }

  public String getFirstName () {
    return firstName;
  }

  public void setFirstName (String firstName) {
    this.firstName = firstName;
  }

  // TO STRING
  public String toString () {
    return String.format("USER : %n" +
        " ID: %f%n" +
        " First Name: %s%n", id, firstName);
  }
}
