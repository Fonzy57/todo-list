package server;

public enum HttpStatusCode {

  // ICI C'EST UN APPEL AU CONSTRUCTEUR
  FOUND(200, "Found"),
  NOT_FOUND(404, "Not found"),
  UNAUTHORIZED(401, "Unauthorized");

  private final int statusCode;
  private final String message;

  HttpStatusCode(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }

}
