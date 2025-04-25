package vn.scrip.middle.repository;
import vn.scrip.middle.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}