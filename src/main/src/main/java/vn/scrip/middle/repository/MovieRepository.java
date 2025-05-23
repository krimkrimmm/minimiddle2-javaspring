package vn.scrip.middle.repository;

import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.enums.MovieType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByName(String name);

    List<Movie> findByNameContaining(String name);

    List<Movie> findByNameContainingIgnoreCase(String name);

    Page<Movie> findByNameContaining(String name, Pageable pageable);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByRatingGreaterThan(Double rating);

    List<Movie> findByRatingLessThan(Double rating, Sort sort);

    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);

    @Query(value = "select * from movies where rating < ?1 order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ1(Double rating);

    @Query(value = "select * from movies where rating < :rating order by rating desc", nativeQuery = true)
    List<Movie> findByRatingLessThanOrderByRatingDesc_NQ2(@Param("rating") Double rating);

    @Query("select m from Movie m where m.rating < ?1 order by m.rating desc")
    List<Movie> findByRatingLessThanOrderByRatingDesc_JPQL(Double rating);

    boolean existsByName(String name);

    long countByRating(Double rating);

    void deleteByName(String name);

    List<Movie> findByStatusTrue();

    Page<Movie> findByTypeAndStatus(MovieType type, Boolean status, Pageable pageable);

    @Query(value = "select * from movies where status = ?1 order by rating desc limit ?2", nativeQuery = true)
    List<Movie> findHotMovie(Boolean status, Integer limit);

    Movie findByIdAndSlugAndStatus(Integer id, String slug, boolean b);

    Optional<Movie> findByIdAndStatusTrue(Integer movieId);

    // ✅ Dành cho API lấy danh sách tập phim theo movieId
    boolean existsById(Integer id);
}
