package services;

import models.task.Task;
import models.user.User;

import java.sql.*;
import java.util.ArrayList;
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

  /**
   * Ajoute un utilisateur en BDD
   *
   * @param user Utilisateur a passé en paramètre
   */
  public void addUser(User user) {
    try {
      // Requête préparée
      PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);

      // Équivalent au bind value
      statement.setString(1, user.getFirstName());
      statement.setString(2, user.getLastName());

      // On exécute la requête
      statement.executeUpdate();

      // Récupérer l'ID généré par la base de données
      // TODO DEMANDER A FLORENT POURQUOI IL FAUT RECUPERER L'ID DIRECTEMENT
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        user.setId(generatedKeys.getLong(1)); // Assigner l'ID généré à l'utilisateur
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Récupère tous les utilisateurs en BDD
   *
   * @return Une liste de tous les utilisateurs
   */
  public List<User> getUsers() {
    List<User> users = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement();
      ResultSet result = stmt.executeQuery(LIST_USERS);

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

  /**
   * Récupère un utilisateur en BDD selon son ID
   *
   * @param id ID de l'utilisateur à récupérer
   * @return Retourne un utilisateur
   */
  public User getUserById(long id) {
    User user = null;

    try {
      PreparedStatement stmt = connection.prepareStatement(GET_USER);
      stmt.setLong(1, id);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        Long idInBdd = result.getLong("id");
        String firstName = result.getString("firstName");
        String lastName = result.getString("lastName");

        user = new User(idInBdd, firstName, lastName);
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return user;
  }

  /**
   * Modifie un utilisateur éxistant
   *
   * @param user Utilisateur
   */
  public void updateUser(User user) {
    if (user.getId() == null) {
      addUser(user); // Si l'ID est null, c'est un nouvel utilisateur, donc on l'ajoute
      return;
    }

    try {
      PreparedStatement stmt = connection.prepareStatement(UPDATE_USER);
      stmt.setString(1, user.getFirstName());
      stmt.setString(2, user.getLastName());
      stmt.setLong(3, user.getId());

      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated == 0) {
        System.err.println("Aucun utilisateur mis à jour, ID inexistant.");
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }


  // -------------
  // --- TASKS ---
  // -------------

  /**
   * Ajout d'une tâche en BDD
   *
   * @param task Tâche à lui passer en paramètre
   */
  public void addTask(Task task) {
    try {
      PreparedStatement statement = connection.prepareStatement(CREATE_TASK);

      statement.setString(1, task.getTitle());
      statement.setString(2, task.getDescription());
      statement.setBoolean(3, task.isDone());
      statement.setLong(4, task.getCreator().getId());

      statement.executeUpdate();

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Méthode qui permet de récupérer toutes les tâches
   *
   * @return Une liste de tâches
   */
  public List<Task> getTasks() {
    List<Task> tasks = new ArrayList<>();
    try {
      Statement stmt = connection.createStatement();
      ResultSet result = stmt.executeQuery(LIST_TASKS);
      while (result.next()) {
        Long id = result.getLong("id");
        String title = result.getString("title");
        String description = result.getString("description");
        boolean done = result.getBoolean("done");
        Long creatorId = result.getLong("creator_id");

        User creator = getUserById(creatorId);

        Task task = new Task(id, title, description, done, creator);

        tasks.add(task);
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return tasks;
  }

  /**
   * Récupère une tâche en BDD selon son ID
   *
   * @param id ID de la tâche à récupérer
   * @return Retourne une tâche
   */
  public Task getTaskById(long id) {
    Task task = null;

    try {
      PreparedStatement stmt = connection.prepareStatement(GET_TASK);
      stmt.setLong(1, id);
      ResultSet result = stmt.executeQuery();
      if (result.next()) {
        Long idInBdd = result.getLong("id");
        String title = result.getString("title");
        String description = result.getString("description");
        boolean done = result.getBoolean("done");
        Long creatorId = result.getLong("creator_id");
        User creator = getUserById(creatorId);

        task = new Task(idInBdd, title, description, done, creator);
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }

    return task;
  }

  /**
   * Modifie une tâche éxistante
   *
   * @param task Tâche à mettre en paramètre
   */
  public void updateTask(Task task) {
    if (task.getId() == null) {
      addTask(task); // Si l'ID est null, c'est un nouvel utilisateur, donc on l'ajoute
      return;
    }

    try {
      PreparedStatement stmt = connection.prepareStatement(UPDATE_TASK);
      stmt.setString(1, task.getTitle());
      stmt.setString(2, task.getDescription());
      stmt.setBoolean(3, task.isDone());
      stmt.setLong(4, task.getId());

      stmt.executeUpdate();

      int rowsUpdated = stmt.executeUpdate();
      if (rowsUpdated == 0) {
        System.err.println("Aucune tâche mise à jour, ID inexistant.");
      }

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

}
