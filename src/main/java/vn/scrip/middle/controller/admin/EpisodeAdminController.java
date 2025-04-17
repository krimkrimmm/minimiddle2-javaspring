package vn.scrip.middle.controller.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.dto.EpisodeRequest;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.service.EpisodeService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/episodes")
@RequiredArgsConstructor
public class EpisodeAdminController {

    private final EpisodeService episodeService;

    @GetMapping
    public Page<Episode> getEpisodesByMovieId(
            @RequestParam Integer movieId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        List<Episode> episodes = episodeService.findEpisodesByMovieId(movieId);
        int start = Math.min(page * pageSize, episodes.size());
        int end = Math.min(start + pageSize, episodes.size());
        List<Episode> pagedEpisodes = episodes.subList(start, end);
        return new PageImpl<>(pagedEpisodes, PageRequest.of(page, pageSize), episodes.size());
    }

    @PostMapping
    public ResponseEntity<?> createEpisode(@Valid @RequestBody EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.createEpisode(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEpisode(@PathVariable Integer id, @Valid @RequestBody EpisodeRequest request) {
        return ResponseEntity.ok(episodeService.updateEpisode(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEpisode(@PathVariable Integer id) {
        episodeService.findEpisodesByMovieId(id);
        return ResponseEntity.noContent().build();
    }
}