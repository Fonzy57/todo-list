package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler {

  public void handle(Socket client) throws IOException {
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

    // Client effectue une requête
    // Permet de répondre au client (le navigateur ou ordinateur)
    new Router().route(client, request);

    reader.close();
    client.close();
  }

}
