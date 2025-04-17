package vn.scrip.middle.repository;
import vn.scrip.middle.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {
}