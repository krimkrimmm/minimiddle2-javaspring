package vn.scrip.middle.repository;
import vn.scrip.middle.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}