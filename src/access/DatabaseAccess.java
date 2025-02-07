package access;

import Exceptions.ElementNotFoundException;
import task.DatedTask;
import task.Task;
import user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseAccess {
  private ArrayList<Task> tasks = new ArrayList<>();
  private ArrayList<User> users = new ArrayList<>();

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
    User steph = new User("Steph");
    User hubert = new User("Hubert");

    Task clean = new Task(
        "Laver la maison",
        "Laver la maison, c'est à dire passer l'aspirateur et la serpillères",
        false,
        steph
    );

    Task cleanCar = new Task(
        "Laver la voiture",
        "Laver la voiture en allant à l'éléphant bleu",
        true,
        steph
    );

    Task shopping = new Task(
        "Faire les courses",
        "Aller à Cora pour faire les courses. Voici la liste des aliments à acheter : \n  Pomme,\n  Dinde,\n  Pâtes," +
            "\n  Riz,\n  Pesto,\n  Pepsi,\n  Monster",
        true,
        hubert
    );

    DatedTask giveProject = new DatedTask(
        "Faire les courses",
        "Aller à Cora Moulins-lès-Metz pour faire les courses.",
        true,
        hubert,
        new Date()
    );

    // Adding tasks
    tasks.add(clean);
    tasks.add(cleanCar);
    tasks.add(shopping);
    tasks.add(giveProject);

    // Adding users
    users.add(steph);
    users.add(hubert);
  }

  // -------------
  // --- TASKS ---
  // -------------

  // LISTING TASKS
  public List<Task> getAllTasks() {
    return tasks;
  }

  // FIND TASK
  public Task getTaskById(long id) throws ElementNotFoundException {
    return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    // OU
//    for (Task task : tasks) {
//      if (task.getId() == id) {
//        return task;
//      }
//    }
//    throw new ElementNotFoundException("La tâche avec l'ID " + id + " n'existe pas");
  }

  // ADD TASK
  public void addTask(Task task) {
    tasks.add(task);
  }

  // DELETE TASK
  public void deleteTaskById(long taskId) {
    tasks.removeIf(t -> t.getId() == taskId);
  }

  // -------------
  // --- USERS ---
  // -------------

  // LISTING USERS
  public List<User> getAllUsers() {
    return users;
  }

  // FIND ONE USER BY ID
  public User getUserById(long id) {
    return users.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
  }

  // ADD USER
  public void addUser(User user) {
    users.add(user);
  }

  // DELETE USER BY ID
  public void deleteUserByID(long userID) {
    users.removeIf(u -> u.getId() == userID);
  }
}
