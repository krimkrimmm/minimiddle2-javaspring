package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.model.dto.EpisodeRequest;
import vn.scrip.middle.service.EpisodeService;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class AdminEpisodeController {

    private final EpisodeService episodeService;

    @GetMapping
    public Page<Episode> getEpisodes(
            @RequestParam Integer movieId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return episodeService.getEpisodes(movieId, page, pageSize);
    }

    @PostMapping
    public ResponseEntity<Episode> createEpisode(@Valid @RequestBody EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.createEpisode(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(
            @PathVariable Integer id,
            @Valid @RequestBody EpisodeRequest request
    ) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id) {
        episodeService.deleteEpisode(id);
        return ResponseEntity.ok().build();
    }
}
