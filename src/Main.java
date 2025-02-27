import org.h2.tools.Server;
import server.ClientHandler;
import services.DatabaseAccess;
import services.DatabaseSeeder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

// TODO ESSAYER DE FAIRE LES TYPES GÉNÉRIQUES POUR LES TASKS ET USERS

public class Main {
  public static void main(String[] args) throws SQLException, IOException {
    DatabaseSeeder dbSeeder = new DatabaseSeeder();
    dbSeeder.seed();

    try (ServerSocket server = new ServerSocket(8080)) {
      while (true) {
        Socket client = server.accept();
        ClientHandler clientHandler = new ClientHandler();
        clientHandler.handle(client);
      }
    }

//    System.out.println(users);
//
//    User user = dba.getUserById(5);
//    if (user != null) {
//      System.out.println("Avant modification : " + user);
//
//      // Modifier les informations
//      user.setFirstName("TINTIN");
//      user.setLastName("MILOU");
//
//      // Enregistrer la mise à jour
//      dba.updateUser(user);
//
//      // Vérifier après mise à jour
//      User updatedUser = dba.getUserById(5);
//      System.out.println("Après modification : " + updatedUser);
//    } else {
//      System.err.println("L'utilisateur avec l'ID 5 n'existe pas.");
//    }
//
//    List<Task> tasks = dba.getTasks();
//    System.out.println(tasks);
//
//    List<Task> tasksDone = dba.getTasksDone();
//    System.out.println("\u001B[32m" + tasksDone + "\u001B[0m");
//
//    Task task = dba.getTaskById(3);
//    System.out.println(task);
//    if (task != null) {
//      System.out.println("Avant modification : " + task);
//
//      // Modifier les informations
//      task.setDescription("Aucune description");
//      task.setTitle("Aucun titre");
//
//      // Enregistrer la mise à jour
//      dba.updateTask(task);
//
//      // Vérifier après mise à jour
//      Task updatedTask = dba.getTaskById(3);
//      System.out.println("Après modification : " + updatedTask);
//    } else {
//      System.err.println("La tâche avec l'ID 5 n'existe pas.");
//    }
//
//    Server server = Server.createWebServer();
//
//    server.start();
//
//    // TODO CHANGER CE WHILE TRUE
//    while (true) {
//    }
    // server.shutdown();

  }
}