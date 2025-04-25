package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.request.UpsertMovieRequest;
import vn.scrip.middle.service.MovieService;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {

    private final MovieService movieService;

    @GetMapping
    public Page<Movie> getAllMovies(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return movieService.getAllMovies(page, pageSize);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody UpsertMovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Integer id,
            @Valid @RequestBody UpsertMovieRequest request
    ) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
