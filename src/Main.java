import server.Request;
import services.DatabaseAccess;
import services.DatabaseSeeder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

// TODO ESSAYER DE FAIRE LES TYPES GENERIQUES POUR LES TASKS ET USERS

public class Main {
  public static void main(String[] args) throws SQLException, IOException {

    DatabaseAccess dba = DatabaseAccess.getInstance();

    DatabaseSeeder dbSeeder = new DatabaseSeeder();
    // dbSeeder.seed();

//    List<User> users = dba.getUsers();
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

    try (ServerSocket server = new ServerSocket(8080)) {

      Socket client = server.accept();
      BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
      Request request = new Request();

      String firstLine = reader.readLine();
      System.out.println(firstLine);

      String[] splitFirstLine = firstLine.split(" ");

      request.setMethod(splitFirstLine[0]);
      request.setPath(splitFirstLine[1]);
      request.setProtocol(splitFirstLine[2]);

      String line;
      while (!(line = reader.readLine()).isBlank()) {
        var h = line.split(": ");
        request.getHeaders().put(h[0], h[1]);
      }

      System.out.println(request);

      // Permet de répondre au client (le navigateur ou ordinateur)
      OutputStream outputStream = client.getOutputStream();

      var responseFirstLine = "HTTP/1.1 200\r\n";
      outputStream.write(responseFirstLine.getBytes());
      outputStream.write("Content-Type: text/html\r\n".getBytes());

      outputStream.write("\r\n".getBytes());
      outputStream.write("<html><body><h1>Hello world</h1></body></html>".getBytes());

      outputStream.write("\r\n\r\n".getBytes());
      outputStream.flush();

      reader.close();
      client.close();
    }

  }
}