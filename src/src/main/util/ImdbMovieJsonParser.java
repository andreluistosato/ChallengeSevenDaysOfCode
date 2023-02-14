package src.main.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import src.main.model.Movie;

public class ImdbMovieJsonParser {

  public static List<Movie> mapJsonToList(String json) {
    List<Movie> movies = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    try {
      movies.addAll(
          mapper.readValue(
              mapper.readTree(json).get("items").toString(), new TypeReference<>() {}));
    } catch (Exception ex) {
      System.out.println("An error occurred during the json parsing");
    }

    return movies;
  }
}
