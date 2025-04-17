package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.dto.MovieRequest;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.service.MovieService;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class MovieAdminController {

    private final MovieService movieService;

    @GetMapping
    public Page<Movie> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return movieService.getAllMovies(page, pageSize);
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.createMovie(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @Valid @RequestBody MovieRequest request) {
        return ResponseEntity.ok(movieService.updateMovie(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
