package src.main.util;

import java.io.PrintWriter;
import java.util.List;
import src.main.model.Movie;

public class HtmlGenerator {

  private final String HEAD =
      """
    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    """;

  private final String BODY = "<body>%s</body>";
  private final String MOVIE =
      """
    <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
      <h4 class="card-header">%s</h4>
      <div class="card-body">
        <img class="card-img" src="%s" alt="%s">
        <p class="card-text mt-2">Imdb Rating: %s - Year: %s</p>
      </div>
    </div>
    """;

  private final PrintWriter printWriter;

  public HtmlGenerator(PrintWriter printWriter) {
    this.printWriter = printWriter;
  }

  public void generateHtml(List<Movie> movies) {
    printWriter.println(HEAD);

    String moviesHtml = generateMovieListHtml(movies);

    printWriter.format(BODY, moviesHtml);
  }

  private String generateMovieListHtml(List<Movie> movies) {
    StringBuilder stringBuilder = new StringBuilder();

    for (Movie movie : movies) {
      stringBuilder.append(
          String.format(
              MOVIE, movie.title, movie.image, movie.title, movie.imDbRating, movie.year));
    }

    return stringBuilder.toString();
  }
}
