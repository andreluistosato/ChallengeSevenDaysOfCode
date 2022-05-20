package src.main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ResourceBundle;

public class Application {

  private static final ResourceBundle resource = ResourceBundle.getBundle("application");

  public static void main(String[] args) {
    String imdbApiKey = resource.getString("imdb-api-key");
    String imdbTop250MoviesUrl =
        String.format("https://imdb-api.com/en/API/Top250Movies/%s", imdbApiKey);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest top250MoviesRequest =
        HttpRequest.newBuilder().GET().uri(URI.create(imdbTop250MoviesUrl)).build();

    client
        .sendAsync(top250MoviesRequest, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(System.out::println)
        .join();
  }
}
