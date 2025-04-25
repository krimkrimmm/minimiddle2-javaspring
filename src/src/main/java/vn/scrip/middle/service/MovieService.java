package vn.scrip.middle.service;

import org.springframework.data.domain.Page;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.enums.MovieType;
import vn.scrip.middle.model.request.UpsertMovieRequest;

import java.util.List;

public interface MovieService {
    List<Movie> findHotMovie(Boolean status, Integer limit);
    Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize);
    Movie findMovieDetails(Integer id, String slug);
    Page<Movie> getAllMovies(Integer page, Integer pageSize);
    Movie createMovie(UpsertMovieRequest request);
    Movie updateMovie(Integer id, UpsertMovieRequest request);
    void deleteMovie(Integer id);
}
