package src.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ResourceBundle;
import src.main.model.Movie;
import src.main.util.HtmlGenerator;
import src.main.util.ImdbApiClient;

public class Application {
  private static final ResourceBundle resource = ResourceBundle.getBundle("application");
  private static final String imdbApiKey = resource.getString("imdb-api-key");

  public static void main(String[] args) throws FileNotFoundException {
    List<Movie> movies = new ImdbApiClient(imdbApiKey).getMoviesFromImdb();

    PrintWriter printWriter = new PrintWriter("MovieList.html");

    HtmlGenerator htmlGenerator = new HtmlGenerator(printWriter);
    htmlGenerator.generateHtml(movies);

    printWriter.close();
  }
}
