package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.dto.MovieRequestDTO;
import vn.scrip.middle.model.dto.MovieResponseDTO;
import vn.scrip.middle.service.MovieService;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<Page<MovieResponseDTO>> list(@RequestParam(defaultValue = "1") int page,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(movieService.getAll(page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Movie> create(@Valid @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Integer id, @Valid @RequestBody MovieRequestDTO dto) {
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
