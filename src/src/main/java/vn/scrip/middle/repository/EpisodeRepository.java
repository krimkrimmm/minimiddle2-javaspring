package vn.scrip.middle.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.scrip.middle.entity.Episode;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    Page<Episode> findAllByMovieId(Integer movieId, Pageable pageable);
}
