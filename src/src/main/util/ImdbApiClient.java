package src.main.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import src.main.model.Movie;

public class ImdbApiClient {
  private static final String imdbApiUrl = "https://imdb-api.com/en/API/Top250Movies/%s";
  private final String imdbApiKey;

  public ImdbApiClient(String imdbApiKey) {
    this.imdbApiKey = imdbApiKey;
  }

  public List<Movie> getMoviesFromImdb() {
    List<Movie> movies = new java.util.ArrayList<>();

    String imdbTop250MoviesUrl = String.format(imdbApiUrl, imdbApiKey);

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest top250MoviesRequest =
        HttpRequest.newBuilder().GET().uri(URI.create(imdbTop250MoviesUrl)).build();

    client
        .sendAsync(top250MoviesRequest, BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenAccept(s -> movies.addAll(ImdbMovieJsonParser.mapJsonToList(s)))
        .join();

    return movies;
  }
}
