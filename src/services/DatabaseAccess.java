package services;

import exceptions.ElementNotFoundException;
import models.task.DatedTask;
import models.task.Task;
import models.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseAccess {
  private static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS USERS (id bigint auto_increment " +
      "primary" +
      " key, " +
      "firstName" +
      " " +
      "varchar(255), lastName varchar(255));";
  private static final String CREATE_TABLE_TASKS = "CREATE TABLE IF NOT EXISTS TASKS (id bigint auto_increment " +
      "primary key, title " +
      "varchar" +
      "(255), description varchar(MAX), done boolean, creator_id bigint);";
  private static final String CREATE_USER = "INSERT INTO USERS (firstName, lastName) VALUES (?, ?)";
  private static final String CREATE_TASK = "INSERT INTO TASKS (title, description, done, creator_id) VALUES (?, ?, " +
      "?," +
      " " +
      "?)";
  private static final String GET_USER = "SELECT * FROM USERS WHERE id = ?";
  private static final String GET_TASK = "SELECT * FROM TASKS WHERE id = ?";
  private static final String UPDATE_USER = "UPDATE USERS SET firstName = ?, lastName = ? WHERE id = ?";
  private static final String UPDATE_TASK = "UPDATE TASKS SET title = ?, description = ?, done= ? WHERE id = ?";
  private static final String LIST_USERS = "SELECT * FROM USERS";
  private static final String LIST_TASKS = "SELECT * FROM TASKS";
  private static final String LIST_TASKS_DONE = "SELECT * FROM TASKS WHERE done = ?";

  private Connection connection;

  // Instance
  private static DatabaseAccess instance;

  // Get the instance
  public static DatabaseAccess getInstance() {
    if (instance == null) {
      instance = new DatabaseAccess();
    }
    return instance;
  }

  // Private constructor
  private DatabaseAccess() {
    try {
      connection = DriverManager.getConnection("jdbc:h2:mem:db1");
      createTables();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Méthode pour créer les tables en BDD
   */
  private void createTables() throws SQLException {
    connection.createStatement().executeUpdate(CREATE_TABLE_USERS);
    connection.createStatement().executeUpdate(CREATE_TABLE_TASKS);
    ResultSet rs = connection.createStatement().executeQuery("SHOW TABLES;");
    while (rs.next()) {
      System.out.println(rs.getString(1));
    }
  }

  // -------------
  // --- USERS ---
  // -------------

  public void addUser(User user) {
    try {
      // Requête préparée
      PreparedStatement statement = connection.prepareStatement(CREATE_USER);

      // Équivalent au bind value
      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());

      // On exécute la requête
      statement.executeUpdate();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public List<User> getUsers() {
    List<User> users = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement();
      ResultSet result = stmt.executeQuery(GET_USER);

      while (result.next()) {
        Long id = result.getLong("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");
        User user = new User(id, firstName, lastName);

        users.add(user);
      }

      return users;

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return users;
  }


  // -------------
  // --- TASKS ---
  // -------------

}
