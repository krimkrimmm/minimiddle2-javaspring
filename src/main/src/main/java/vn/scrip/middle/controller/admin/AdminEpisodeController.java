package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.dto.EpisodeRequestDTO;
import vn.scrip.middle.model.dto.MovieRequestDTO;
import vn.scrip.middle.service.EpisodeService;
import vn.scrip.middle.service.MovieService;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class AdminEpisodeController {
    private final EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<Page<Episode>> list(@RequestParam Long movieId,
                                              @RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(episodeService.getByMovieId(movieId, page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Episode> create(@Valid @RequestBody EpisodeRequestDTO dto) {
        return ResponseEntity.ok(episodeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> update(@PathVariable Integer id, @Valid @RequestBody EpisodeRequestDTO dto) {
        return ResponseEntity.ok(episodeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        episodeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
