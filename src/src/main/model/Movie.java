package src.main.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {
  @JsonProperty("id")
  public String id;

  @JsonProperty("rank")
  public String rank;

  @JsonProperty("title")
  public String title;

  @JsonProperty("fullTitle")
  public String fullTitle;

  @JsonProperty("year")
  public String year;

  @JsonProperty("image")
  public String image;

  @JsonProperty("crew")
  public String crew;

  @JsonProperty("imDbRating")
  public String imDbRating;

  @JsonProperty("imDbRatingCount")
  public String imDbRatingCount;
}
