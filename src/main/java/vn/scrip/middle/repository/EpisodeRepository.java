package vn.scrip.middle.repository;
import vn.scrip.middle.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovie_IdAndStatusOrderByDisplayOrderAsc(Integer id, Boolean status);

    Episode findByMovie_IdAndDisplayOrderAndStatus(Integer id, Integer displayOrder, Boolean status);
}