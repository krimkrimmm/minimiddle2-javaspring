package vn.scrip.middle.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.scrip.middle.exception.NotFoundException;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.dto.MovieRequestDTO;
import vn.scrip.middle.model.dto.MovieResponseDTO;
import vn.scrip.middle.repository.CountryRepository;
import vn.scrip.middle.repository.MovieRepository;
import vn.scrip.middle.service.MovieService;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepo;
    private final CountryRepository countryRepo;

    @Override
    public Page<MovieResponseDTO> getAll(int page, int pageSize) {
        return movieRepo.findAll(PageRequest.of(page - 1, pageSize))
                .map(this::mapToResponseDTO);
    }

    @Override
    public Movie create(MovieRequestDTO dto) {
        Movie movie = new Movie();
        mapDtoToEntity(dto, movie);
        return movieRepo.save(movie);
    }

    @Override
    public Movie update(Integer id, MovieRequestDTO dto) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        mapDtoToEntity(dto, movie);
        return movieRepo.save(movie);
    }

    @Override
    public void delete(Integer id) {
        if (!movieRepo.existsById(id)) {
            throw new NotFoundException("Movie not found");
        }
        movieRepo.deleteById(id);
    }

    private void mapDtoToEntity(MovieRequestDTO dto, Movie movie) {
        movie.setName(dto.getName());
        movie.setTrailerUrl(dto.getTrailerUrl());
        movie.setDescription(dto.getDescription());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setType(dto.getType());
        movie.setStatus(dto.getStatus());
        movie.setCountry(countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new NotFoundException("Country not found")));
        // TODO: handle genreIds, actorIds, directorIds
    }

    private MovieResponseDTO mapToResponseDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getName(),
                movie.getTrailerUrl(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getType(),
                movie.getStatus(),
                movie.getCountry().getName()
        );
    }
}
