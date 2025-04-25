package vn.scrip.middle.service;

import org.springframework.data.domain.Page;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.model.dto.EpisodeRequest;

import java.util.List;

public interface EpisodeService {
    Page<Episode> getEpisodes(Integer movieId, int page, int pageSize);
    Episode createEpisode(EpisodeRequest request);
    Episode updateEpisode(Integer id, EpisodeRequest request);
    void deleteEpisode(Integer id);

    // ➔ THÊM HÀM NÀY:
    List<Episode> findEpisodesByMovieId(Integer movieId);

    // ➔ Nếu bạn có dùng findEpisodeByDisplayOrder, cũng thêm:
    Episode findEpisodeByDisplayOrder(Integer movieId, String displayOrder);
}