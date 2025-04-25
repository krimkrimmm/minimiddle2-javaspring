package vn.scrip.middle.service;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.exception.BadRequestException;
import vn.scrip.middle.exception.NotFoundException;
import vn.scrip.middle.model.request.CreateEpisodeRequest;

import vn.scrip.middle.model.request.UpdateEpisodeRequest;
import vn.scrip.middle.repository.EpisodeRepository;
import vn.scrip.middle.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;

    public List<Episode> findEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovie_IdAndStatusOrderByDisplayOrderAsc(id, true);
    }

    public Page<Episode> getEpisodeListOfMovieByAdmin(Integer movieId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("displayOrder").ascending());
        return episodeRepository.findByMovie_Id(movieId, pageable);
    }

    public Episode findEpisodeByDisplayOrder(Integer id, String tap) {
        Integer displayOrder = tap.equals("full") ? 1 : Integer.parseInt(tap);
        // select * from episodes where movie_id = id and display_order = displayOrder and status = true
        return episodeRepository.findByMovie_IdAndDisplayOrderAndStatus(id, displayOrder, true);
    }

    public Episode createEpisode(CreateEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim với id " + request.getMovieId()));

        if (episodeRepository.existsByMovie_IdAndDisplayOrder(movie.getId(), request.getDisplayOrder())) {
            throw new BadRequestException("Thứ tự tập phim không được trùng nhau");
        }

        Episode episode = Episode.builder()
                .name(request.getName())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(request.getStatus() ? LocalDateTime.now() : null)
                .movie(movie)
                .build();
        return episodeRepository.save(episode);
    }

    public Episode updateEpisode(Integer id, UpdateEpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim có id = " + id));

        if (!episode.getDisplayOrder().equals(request.getDisplayOrder()) && episodeRepository.existsByMovie_IdAndDisplayOrder(episode.getMovie().getId(), request.getDisplayOrder())) {
            throw new BadRequestException("Thứ tự tập phim không được trùng nhau");
        }

        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setUpdatedAt(LocalDateTime.now());
        episode.setPublishedAt(request.getStatus() ? LocalDateTime.now() : null);
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tập phim có id = " + id));

        // TODO: Xóa video trên cloudinary
        episodeRepository.delete(episode);
    }
}