package vn.scrip.middle.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.exception.NotFoundException;
import vn.scrip.middle.model.dto.EpisodeRequest;
import vn.scrip.middle.repository.EpisodeRepository;
import vn.scrip.middle.repository.MovieRepository;
import vn.scrip.middle.service.EpisodeService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final MovieRepository movieRepository;

    @Override
    public Page<Episode> getEpisodes(Integer movieId, int page, int pageSize) {
        if (!movieRepository.existsById(movieId)) {
            throw new NotFoundException("Movie not found");
        }
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return episodeRepository.findAllByMovieId(movieId, pageable); // ✅ sửa đúng tên method
    }

    @Override
    public Episode createEpisode(EpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        Episode episode = new Episode();
        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        episode.setMovie(movie);
        return episodeRepository.save(episode);
    }

    @Override
    public Episode updateEpisode(Integer id, EpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Episode not found"));
        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        return episodeRepository.save(episode);
    }

    @Override
    public void deleteEpisode(Integer id) {
        if (!episodeRepository.existsById(id)) {
            throw new NotFoundException("Episode not found");
        }
        episodeRepository.deleteById(id);
    }

    @Override
    public List<Episode> findEpisodesByMovieId(Integer movieId) {
        return List.of();
    }

    @Override
    public Episode findEpisodeByDisplayOrder(Integer movieId, String displayOrder) {
        return null;
    }
}
