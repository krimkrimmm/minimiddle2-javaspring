package vn.scrip.middle.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.scrip.middle.exception.NotFoundException;
import vn.scrip.middle.model.dto.EpisodeDTO;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.dto.EpisodeRequestDTO;
import vn.scrip.middle.repository.EpisodeRepository;
import vn.scrip.middle.repository.MovieRepository;
import vn.scrip.middle.service.EpisodeService;

@Service
@RequiredArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepo;
    private final MovieRepository movieRepo;

    @Override
    public Page<Episode> getByMovieId(Integer movieId, int page, int pageSize) {
        if (!movieRepo.existsById(movieId)) {
            throw new NotFoundException("Movie not found");
        }
        return episodeRepo.findByMovieId(movieId, PageRequest.of(page - 1, pageSize));
    }

    @Override
    public Episode create(EpisodeRequestDTO dto) {
        Movie movie = movieRepo.findById(dto.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        Episode ep = new Episode();
        ep.setName(dto.getName());
        ep.setDisplayOrder(dto.getDisplayOrder());
        ep.setStatus(dto.getStatus());
        ep.setMovie(movie);
        return episodeRepo.save(ep);
    }

    @Override
    public Episode update(Long id, EpisodeRequestDTO dto) {
        Episode ep = episodeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Episode not found"));
        ep.setName(dto.getName());
        ep.setDisplayOrder(dto.getDisplayOrder());
        ep.setStatus(dto.getStatus());
        return episodeRepo.save(ep);
    }

    @Override
    public void delete(Integer id) {
        if (!episodeRepo.existsById(id)) {
            throw new NotFoundException("Episode not found");
        }
        episodeRepo.deleteById(id);
    }
}
