package src.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.ResourceBundle;
import src.main.model.Movie;

public class Application {
  private static final ResourceBundle resource = ResourceBundle.getBundle("application");

  private static final String imdbApiUrl = "https://imdb-api.com/en/API/Top250Movies/%s";

  public static void main(String[] args) {
    String imdbApiKey = resource.getString("imdb-api-key");
    String imdbTop250MoviesUrl = String.format(imdbApiUrl, imdbApiKey);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest top250MoviesRequest =
        HttpRequest.newBuilder().GET().uri(URI.create(imdbTop250MoviesUrl)).build();

    ObjectMapper mapper = new ObjectMapper();
    List<Movie> movies = new java.util.ArrayList<>();

    client
        .sendAsync(top250MoviesRequest, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(
            s -> {
              try {
                movies.addAll(
                    mapper.readValue(
                        mapper.readTree(s).get("items").toString(), new TypeReference<>() {}));

              } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
              }
            })
        .join();

    System.out.println(movies);
  }
}
