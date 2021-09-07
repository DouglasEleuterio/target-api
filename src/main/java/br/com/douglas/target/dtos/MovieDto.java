package br.com.douglas.target.dtos;

import br.com.douglas.target.models.Movie;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class MovieDto {

    @NotBlank
    private String title;

    @NotBlank
    private Date releaseDate;
    private String genre;
    private String overview;
    private String coverImage;
    private Double rating;

    public static MovieDto toDto(Movie movie) {
        return new MovieDto(movie.getTitle(), movie.getReleaseDate(), movie.getGenre(), movie.getOverview(), movie.getCoverImage(), movie.getRating());
    }

    public Movie toMovie(){
        return new Movie(title, releaseDate,genre, overview, coverImage, rating);
    }

    public MovieDto(String title, Date releaseDate, String genre, String overview, String coverImage, Double rating) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.overview = overview;
        this.coverImage = coverImage;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
