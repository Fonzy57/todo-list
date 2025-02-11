import services.DatabaseAccess;
import models.task.Task;
import models.task.TaskBuilder;
import models.user.User;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    long idToSearchTask = 5;
    long idToSearchUser = 1;

    DatabaseAccess bdd = DatabaseAccess.getInstance();

    // TASKS
    List<Task> allTasks = bdd.getAllTasks();

    try {
      Task taskId1 = bdd.getTaskById(idToSearchTask);
      System.out.println(taskId1);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    Task newTask = new Task(
        "Dormir",
        "Se coucher tôt pour être en forme pour le cours JAVA",
        false,
        bdd.getUserById(0)
    );

    Task testBuilder = new TaskBuilder()
        .setCreator(bdd.getUserById(0))
        .setDone(true)
        .setTitle("Mon titre")
        .setDescription("Ma description")
        .build();

    bdd.addTask(newTask);
    bdd.addTask(testBuilder);

    bdd.deleteTaskById(0);

    displayAllTasks(allTasks);


    // USERS
    List<User> allUsers = bdd.getAllUsers();
    displayAllUsers(allUsers);

    User userId1 = bdd.getUserById(idToSearchUser);
    User newUser = new User("Victor");
    bdd.addUser(newUser);

    displayAllUsers(allUsers);

    System.out.println("User avec l'ID " + idToSearchUser + " : \n" + userId1);

    bdd.deleteUserByID(1);

    displayAllUsers(allUsers);
  }

  public static void displayAllTasks(List<Task> allTasks) {
    System.out.println("ALL TASKS : \n");
    for (Task task : allTasks) {
      System.out.println(task);
    }
    System.out.println("\n ----------------------------------------------------- \n");
  }

  public static void displayAllUsers(List<User> allUsers) {
    System.out.println("ALL USERS : \n");
    for (User user : allUsers) {
      System.out.println(user);
    }
    System.out.println("\n ----------------------------------------------------- \n");
  }

}