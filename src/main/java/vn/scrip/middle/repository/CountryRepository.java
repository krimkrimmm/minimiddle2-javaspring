package vn.scrip.middle.repository;
import vn.scrip.middle.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CountryRepository extends JpaRepository<Country, Integer> {
}