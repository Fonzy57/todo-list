package server;

import models.task.Task;
import models.user.User;
import services.DatabaseAccess;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class Router {
  private final DatabaseAccess dba = DatabaseAccess.getInstance();

  private String myStyle = "* {\n" +
      "  margin: 0;\n" +
      "  padding: 0;\n" +
      "  box-sizing: border-box;\n" +
      "}\n" +
      "\n" +
      "body {\n" +
      "  font-family: \"Open Sans\", sans-serif;\n" +
      "  line-height: normal;\n" +
      "  padding: 60px 100px;\n" +
      "}\n" +
      "\n" +
      "h1,\n" +
      "h2,\n" +
      "h3,\n" +
      "h4,\n" +
      "h5,\n" +
      "h6 {\n" +
      "  font-family: \"Montserrat\", sans-serif;\n" +
      "  letter-spacing: 2px;\n" +
      "  text-transform: uppercase;\n" +
      "}\n" +
      "\n" +
      ":root {\n" +
      "  --main: #193256;\n" +
      "  --main-bis: #215aa7;\n" +
      "  --main-light: #c7e3f8;\n" +
      "  --secondary: #f2be2a;\n" +
      "  --secondary-bis: #efd670;\n" +
      "  --secondary-light: #e1d6a8;\n" +
      "  --black: #1d1d1d;\n" +
      "  --white: #fff;\n" +
      "  --success: #019a4a;\n" +
      "  --warning: #ff9900;\n" +
      "  --error: #ff0000;\n" +
      "  --disabled: #f5f5f5;\n" +
      "}\n" +
      "\n" +
      "a {\n" +
      "  text-decoration: none;\n" +
      "  color: inherit;\n" +
      "}" +
      ".title {\n" +
      "  color: var(--main-bis);\n" +
      "  margin-top: 20px;\n" +
      "}" +
      ".users {\n" +
      "  display: flex;\n" +
      "  align-items: flex-start;\n" +
      "  flex-direction: column;\n" +
      "  gap: 8px;\n" +
      "  justify-content: center;\n" +
      "}" +
      ".container {\n" +
      "  max-width: 800px;\n" +
      "}" +
      ".button {\n" +
      "  font-family: \"Montserrat\", sans-serif;\n" +
      "  letter-spacing: 1px;\n" +
      "  color: var(--white);\n" +
      "  font-weight: 600;\n" +
      "  display: block;\n" +
      "  padding: 10px 18px;\n" +
      "  width: max-content;\n" +
      "  border-radius: 8px;\n" +
      "  transition: all 0.3s ease-out;\n" +
      "  font-size: 20px;\n" +
      "  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);\n" +
      "}\n" +
      "\n" +
      "/* --- PRIMARY BUTTON --- */\n" +
      ".button-primary {\n" +
      "  background-color: var(--main);\n" +
      "}\n" +
      "\n" +
      ".button-primary:hover {\n" +
      "  background-color: var(--main-bis);\n" +
      "}\n" +
      "\n" +
      ".button-primary:active {\n" +
      "  background-color: var(--main-bis);\n" +
      "  box-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1),\n" +
      "    0 0 0 2px var(--main);\n" +
      "}" +
      ".button-container {\n" +
      "  margin-top: 20px;\n" +
      "  display: flex;\n" +
      "  align-items: center;\n" +
      "  justify-content: flex-start;\n" +
      "  gap: 8px;\n" +
      "}" +
      ".task-container {\n" +
      "  display: flex;\n" +
      "  align-items: flex-start;\n" +
      "  gap: 8px;\n" +
      "  flex-direction: column;\n" +
      "  margin-top: 15px;\n" +
      "  border: 1px solid var(--main-bis);\n" +
      "  border-radius: 8px;\n" +
      "  padding: 20px;\n" +
      "  cursor: pointer;\n" +
      "  transition: box-shadow 0.3s ease-in-out;\n" +
      "}" +
      ".task-container:hover {\n" +
      "  box-shadow: 0 20px 25px -5px rgb(0 0 0 / 0.1),\n" +
      "    0 8px 10px -6px rgb(0 0 0 / 0.1)\n" +
      "}" +
      ".one-task-container {\n" +
      "  display: flex;\n" +
      "  flex-direction: column;\n" +
      "  align-items: flex-start;\n" +
      "  gap: 8px;\n" +
      "  margin-top: 20px;\n" +
      "}" +
      ".green {\n" +
      "  color: #019a4a;\n" +
      "  font-weight: bold;\n" +
      "}\n" +
      "\n" +
      ".red {\n" +
      "  color: #ff0000;\n" +
      "  font-weight: bold;\n" +
      "}" +
      ".underline {\n" +
      "  text-decoration: underline;\n" +
      "}" +
      ".bold {\n" +
      "  font-weight: bold;\n" +
      "}";

  public void route(Socket client, Request request) throws IOException {

    switch (request.getPath()) {
      case "/users":
        handleUsersPath(client);
        break;

      case "/users.json":
        handleUsersJson(client);
        break;
      case "/tasks":
        handleTasks(client);
        break;
      case "/task":
        handleOneTask(client, request);
        break;
      case "/":
        handleIndex(client);
        break;
      default:
        handle404(client);
        break;
    }
  }

  /**
   * Affiche la page index du serveur
   *
   * @param client Communication entre 2 machines (client et serveur)
   * @throws IOException I/O exception of some sort has occurred
   */
  private void handleIndex(Socket client) throws IOException {
    OutputStream outputStream = client.getOutputStream();

    var responseFirstLine = "HTTP/1.1 200\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: text/html\r\n".getBytes());
    outputStream.write("\r\n".getBytes());

    StringBuilder myHtmlBuilder = new StringBuilder();
    myHtmlBuilder.append("<!DOCTYPE html>\r\n");
    myHtmlBuilder.append("<html><head>\n" +
                             "  <meta charset=\"UTF-8\" />\n" +
                             "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                             "  <title>Document</title>\n" +
                             "  <link rel=\"stylesheet\" href=\"styles/styles.css\">" +
                             "<style>" +
                             myStyle +
                             "</style>" +
                             "</head>");
    myHtmlBuilder.append("<body style=\"display:flex; align-items:center; justify-content:center;\">");
    myHtmlBuilder.append("<div>");

    myHtmlBuilder.append("<h1 class=\"title\" style=\"text-align: center;\">Application Todo List</h1>");

    myHtmlBuilder.append(
        "<div class=\"button-container\">\n");
    myHtmlBuilder.append("<a href=\"users\" class=\"button button-primary\">Voir les utilisateurs</a>");
    myHtmlBuilder.append("<a href=\"tasks\" class=\"button button-primary\">Voir les tâches</a>");
    myHtmlBuilder.append("</div>");
    myHtmlBuilder.append("</div>");
    myHtmlBuilder.append("</body></html>");

    outputStream.write(myHtmlBuilder.toString().getBytes());

    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }


  // ICI EXEMPLE D'UN RENVOIE AU FORMAT JSON SUR L'URL /users.json
  public void handleUsersJson(Socket client) throws IOException {
    OutputStream outputStream = client.getOutputStream();

    var responseFirstLine = "HTTP/1.1 200\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: application/json\r\n".getBytes());

    outputStream.write("\r\n".getBytes());

    List<User> users = dba.getUsers();

    StringBuilder myJson = new StringBuilder();
    myJson.append("[");
    for (User user : users) {
      myJson.append("{\r\n");
      myJson.append("  \"id\": ");
      myJson.append(user.getId());
      myJson.append(",\r\n");
      myJson.append("  \"firstName\":\" ");
      myJson.append(user.getFirstName());
      myJson.append("\",\r\n");
      myJson.append("  \"lastName\":\" ");
      myJson.append(user.getLastName());
      myJson.append("\",\r");
      myJson.append("\r\n}\r\n");
    }

    myJson.append("]");

    outputStream.write(myJson.toString().getBytes());
    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }


  /**
   * Affiche la page des utilisateurs
   *
   * @param client Communication entre 2 machines (client et serveur)
   * @throws IOException I/O exception of some sort has occurred
   */
  private void handleUsersPath(Socket client) throws IOException {
    OutputStream outputStream = client.getOutputStream();

    var responseFirstLine = "HTTP/1.1 200\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: text/html\r\n".getBytes());

    outputStream.write("\r\n".getBytes());

    List<User> users = dba.getUsers();

    StringBuilder myHtmlBuilder = new StringBuilder();
    myHtmlBuilder.append("<!DOCTYPE html>\r\n");
    myHtmlBuilder.append("<html><head>\n" +
                             "  <meta charset=\"UTF-8\" />\n" +
                             "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                             "  <title>Document</title>\n" +
                             "  <link rel=\"stylesheet\" href=\"styles/styles.css\">" +
                             "<style>" +
                             myStyle +
                             "</style>" +
                             "</head>");
    myHtmlBuilder.append("<body>");
    myHtmlBuilder.append("<h1 class=\"title\">Mes utilisateurs</h1>");
    myHtmlBuilder.append("<div class=\"users container\" style=\"margin-top: 20px;\">");
    for (User user : users) {
      myHtmlBuilder.append("<p>(" + user.getId() + ") " + user.getFirstName() + " " + user.getLastName() + "</p>");
    }
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("<div class=\"button-container\">\n");
    myHtmlBuilder.append("<a href=\"/\" class=\"button button-primary\">Accueil</a>");
    myHtmlBuilder.append("<a href=\"tasks\" class=\"button button-primary\">Voir les tâches</a>");
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("</body></html>");

    outputStream.write(myHtmlBuilder.toString().getBytes());

    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }

  /**
   * Affiche la page des tâches
   *
   * @param client Communication entre 2 machines (client et serveur)
   * @throws IOException I/O exception of some sort has occurred
   */
  private void handleTasks(Socket client) throws IOException {
    OutputStream outputStream = client.getOutputStream();

    var responseFirstLine = "HTTP/1.1 200\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: text/html\r\n".getBytes());

    outputStream.write("\r\n".getBytes());

    List<Task> tasks = dba.getTasks();

    StringBuilder myHtmlBuilder = new StringBuilder();
    myHtmlBuilder.append("<!DOCTYPE html>\r\n");
    myHtmlBuilder.append("<html><head>\n" +
                             "  <meta charset=\"UTF-8\" />\n" +
                             "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                             "  <title>Document</title>\n" +
                             "  <link rel=\"stylesheet\" href=\"styles/styles.css\">" +
                             "<style>" +
                             myStyle +
                             "</style>" +
                             "</head>");
    myHtmlBuilder.append("<body>");
    myHtmlBuilder.append("<h1 class=\"title\">Mes tâches</h1>");
    myHtmlBuilder.append("<div class=\"users container\" style=\"margin-top: 20px;\">");
    for (Task task : tasks) {
      myHtmlBuilder.append("<div class=\"task-container\" onclick=\"window.location.href='/task?id=" + task.getId() + "';\">");
      myHtmlBuilder.append("<p style=\"font-weight: bold;\">Titre : " + task.getTitle() + " (ID: " + task.getId() +
                               ")</p>");
      myHtmlBuilder.append("<p>Description : " + task.getDescription() + ")</p>");
      myHtmlBuilder.append("</div>");
    }
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("<div class=\"button-container\">\n");
    myHtmlBuilder.append("<a href=\"/\" class=\"button button-primary\">Accueil</a>");
    myHtmlBuilder.append("<a href=\"users\" class=\"button button-primary\">Voir les utilisateurs</a>");
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("</body></html>");

    outputStream.write(myHtmlBuilder.toString().getBytes());

    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }

  /**
   * Affiche la page d'une tâche
   *
   * @param client Communication entre 2 machines (client et serveur)
   * @throws IOException I/O exception of some sort has occurred
   */
  private void handleOneTask(Socket client, Request request) throws IOException {
    OutputStream outputStream = client.getOutputStream();
    var taskId = request.getParams().get("id");

    var responseFirstLine = "HTTP/1.1 200\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: text/html\r\n".getBytes());

    outputStream.write("\r\n".getBytes());

    Task task = dba.getTaskById(Long.parseLong(taskId));
    User user = dba.getUserById(task.getCreator().getId());

    String doneString = task.isDone() ? "<span class=\"green\">est faite</span>" : "<span class=\"red\">n'est pas " +
        "faite</span>";

    StringBuilder myHtmlBuilder = new StringBuilder();
    myHtmlBuilder.append("<!DOCTYPE html>\r\n");
    myHtmlBuilder.append("<html><head>\n" +
                             "  <meta charset=\"UTF-8\" />\n" +
                             "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                             "  <title>Document</title>\n" +
                             "  <link rel=\"stylesheet\" href=\"styles/styles.css\">" +
                             "<style>" +
                             myStyle +
                             "</style>" +
                             "</head>");
    myHtmlBuilder.append("<body>");
    myHtmlBuilder.append("<div class=\"one-task-container container\">\n");
    myHtmlBuilder.append("<h1 class=\"title\">Tâche : " + task.getTitle() + "</h1>");
    myHtmlBuilder.append("<p class=\"\"><span class=\"underline bold\">Description :</span> " + task.getDescription() +
                             "</p>");
    myHtmlBuilder.append("<p class=\"\">La tâche " + doneString + "</p>");
    myHtmlBuilder.append("<p class=\"\">Tâche à faire par : " + user.getFirstName() + " " + user.getLastName().toUpperCase() +
                             "</p>");
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("<div class=\"button-container\">\n");
    myHtmlBuilder.append("<a href=\"/\" class=\"button button-primary\">Accueil</a>");
    myHtmlBuilder.append("<a href=\"/tasks\" class=\"button button-primary\">Voir les tâches</a>");
    myHtmlBuilder.append("<a href=\"users\" class=\"button button-primary\">Voir les utilisateurs</a>");
    myHtmlBuilder.append("</div>");

    myHtmlBuilder.append("</body></html>");

    outputStream.write(myHtmlBuilder.toString().getBytes());

    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }

  /**
   * Affiche la page 404
   *
   * @param client Communication entre 2 machines (client et serveur)
   * @throws IOException I/O exception of some sort has occurred
   */
  private void handle404(Socket client) throws IOException {
    OutputStream outputStream = client.getOutputStream();

    var responseFirstLine = "HTTP/1.1 404\r\n";
    outputStream.write(responseFirstLine.getBytes());
    outputStream.write("Content-Type: text/html\r\n".getBytes());
    outputStream.write("\r\n".getBytes());

    StringBuilder myHtmlBuilder = new StringBuilder();
    myHtmlBuilder.append("<!DOCTYPE html>\r\n");
    myHtmlBuilder.append("<html><head>\n" +
                             "  <meta charset=\"UTF-8\" />\n" +
                             "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                             "  <title>Document</title>\n" +
                             "</head>");
    myHtmlBuilder.append("<body>");
    myHtmlBuilder.append("<h1 class=\"title\">404 not found</h1>");
    myHtmlBuilder.append("</body></html>");
    outputStream.write(myHtmlBuilder.toString().getBytes());
    outputStream.write("\r\n\r\n".getBytes());
    outputStream.flush();
    outputStream.close();
  }
}
