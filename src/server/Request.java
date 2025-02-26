package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Request {
  private String method;
  private String path;
  private String protocol;
  private Map<String, String> headers = new HashMap<>();

  public void setHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public Map<String, String> getHeaders() {
    return headers;
  }

  // TODO VOIR POUR PASSER UN HEADER POUR L'ID
  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  public String getHeader(String key) {
    return this.headers.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Request request = (Request) o;
    return Objects.equals(method, request.method) && Objects.equals(
        path,
        request.path
    ) && Objects.equals(protocol, request.protocol);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, path, protocol);
  }

  @Override
  public String toString() {
    return "Request{" +
        "method='" + method + '\'' +
        ", path='" + path + '\'' +
        ", protocol='" + protocol + '\'' +
        ", headers=" + headers +
        '}';
  }
}
