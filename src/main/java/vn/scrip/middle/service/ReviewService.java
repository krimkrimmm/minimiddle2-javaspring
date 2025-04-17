package vn.scrip.middle.service;
import vn.scrip.middle.entity.Movie;
import vn.scrip.middle.entity.Review;
import vn.scrip.middle.entity.User;
import vn.scrip.middle.exception.BadRequestException;
import vn.scrip.middle.exception.NotFoundException;

import vn.scrip.middle.model.request.CreateReviewRequest;
import vn.scrip.middle.model.request.UpdateReviewRequest;
import vn.scrip.middle.repository.MovieRepository;
import vn.scrip.middle.repository.ReviewRepository;
import vn.scrip.middle.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public Page<Review> getReviewsByMovie(Integer movieId, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return reviewRepository.findByMovie_Id(movieId, pageable);
    }
    public Review createReview(CreateReviewRequest request) {
        // TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));

        Movie movie = movieRepository.findByIdAndStatusTrue(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Không tìm thấy phim có id = " + request.getMovieId()));
        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .movie(movie)
                .user(user)
                .build();
        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        // TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy review có id = " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Không có quyền cập nhật review");
        }

        review.setContent(request.getContent());
        review.setRating(request.getRating());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // TODO: Fix login user
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy user có id = " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy review có id = " + id));

        // Check user is owner of review
        if (!review.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Không có quyền xóa review");
        }
        reviewRepository.delete(review);
    }
}