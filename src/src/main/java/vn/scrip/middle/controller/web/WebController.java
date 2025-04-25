package vn.scrip.middle.controller.web;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.scrip.middle.entity.Episode;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.model.dto.UserDTO;
import vn.scrip.middle.model.enums.MovieType;
import vn.scrip.middle.service.EpisodeService;
import vn.scrip.middle.service.MovieService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final MovieService movieService;
    private final EpisodeService episodeService;
    private final HttpSession session;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("hotMovies", movieService.findHotMovie(true, 4));
        model.addAttribute("phimLeList", movieService.findByType(MovieType.PHIM_LE, true, 1, 6).getContent());
        model.addAttribute("phimBoList", movieService.findByType(MovieType.PHIM_BO, true, 1, 6).getContent());
        model.addAttribute("phimChieuRapList", movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, 1, 6).getContent());
        return "web/index";
    }

    @GetMapping("/phim-bo")
    public String getPhimBoPage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_BO, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-bo";
    }

    @GetMapping("/phim-le")
    public String getPhimLePage(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "18") Integer pageSize,
                                Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_LE, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRapPage(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "18") Integer pageSize,
                                      Model model) {
        Page<Movie> moviePage = movieService.findByType(MovieType.PHIM_CHIEU_RAP, true, page, pageSize);
        model.addAttribute("moviePage", moviePage);
        model.addAttribute("currentPage", page);
        return "web/phim-chieu-rap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieDetailsPage(@PathVariable Integer id,
                                      @PathVariable String slug,
                                      Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);

        model.addAttribute("movie", movie);
        model.addAttribute("episodes", episodes);

        // Placeholder kiểm tra phim yêu thích (sau này nối FavoriteService)
        boolean isFavorite = false;
        model.addAttribute("isFavorite", isFavorite);

        return "web/chi-tiet-phim";
    }

    @GetMapping("/xem-phim/{id}/{slug}")
    public String getWatchMovieDetailsPage(@PathVariable Integer id,
                                           @PathVariable String slug,
                                           @RequestParam String tap,
                                           Model model) {
        Movie movie = movieService.findMovieDetails(id, slug);
        List<Episode> episodes = episodeService.findEpisodesByMovieId(id);
        Episode episode = episodeService.findEpisodeByDisplayOrder(id, tap);

        model.addAttribute("movie", movie);
        model.addAttribute("episodes", episodes);
        model.addAttribute("episode", episode);

        return "web/xem-phim";
    }

    @GetMapping("/phim-yeu-thich")
    public String getFavoritePage(Model model) {
        return "web/phim-yeu-thich";
    }

    @GetMapping("/dang-nhap")
    public String showLoginPage() {
        UserDTO currentUser = (UserDTO) session.getAttribute("currentUser");
        return (currentUser == null) ? "web/login" : "redirect:/";
    }
}
