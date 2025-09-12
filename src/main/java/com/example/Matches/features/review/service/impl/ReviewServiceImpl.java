package com.example.Matches.features.review.service.impl;

import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.review.entity.Review;
import com.example.Matches.features.review.payload.request.ReviewRequestDto;
import com.example.Matches.features.review.payload.response.ReviewResponseDto;
import com.example.Matches.features.review.payload.response.UserProfileWithReviewsDto;
import com.example.Matches.features.review.repository.ReviewRepository;
import com.example.Matches.features.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final UserRepo userRepository;

    public ReviewServiceImpl( ReviewRepository reviewRepository, UserRepo userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }


    public Review addReview(ReviewRequestDto reviewRequestDto) {
        User reviewer = userRepository.findById(reviewRequestDto.getReviewerId())
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));
        User reviewedUser = userRepository.findById(reviewRequestDto.getReviewedUserId())
                .orElseThrow(() -> new RuntimeException("Reviewed user not found"));

        Review review = new Review();
        review.setReview(reviewRequestDto.getReviewText());
        review.setReviewer(reviewer);
        review.setReviewedUser(reviewedUser);
        review.setRating(reviewRequestDto.getRating());

        return reviewRepository.save(review);
    }




//    public ProfileResponseDto getProfileWithReviews(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<ReviewResponseDto> reviews = reviewRepository.findByReviewedUser(user)
//                .stream()
//                .map(r -> {
//                    ReviewResponseDto dto = new ReviewResponseDto();
//                    dto.setId(r.getId());
//                    dto.setReviewText(r.getReviewText());
//                    dto.setReviewerId(r.getReviewer().getId());
//                    dto.setReviewerName(r.getReviewer().getUsername());
//                    dto.setReviewerEmail(r.getReviewer().getEmail());
//                    dto.setReviewerProfilePic(r.getReviewer().getProfilePicUrl());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//
//        ProfileResponseDto profileDto = new ProfileResponseDto();
//        profileDto.setId(user.getId());
//        profileDto.setUsername(user.getUsername());
//        profileDto.setEmail(user.getEmail());
//        profileDto.setProfilePicUrl(user.getProfilePicUrl());
//        profileDto.setBio(user.getBio());
//        profileDto.setReviewsReceived(reviews);
//
//        return profileDto;
//    }
//
//    // Optional: Fetch reviews given by a user
//    public List<ReviewResponseDto> getReviewsGivenByUser(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return user.getReviewsGiven().stream()
//                .map(r -> {
//                    ReviewResponseDto dto = new ReviewResponseDto();
//                    dto.setId(r.getId());
//                    dto.setReviewText(r.getReviewText());
//                    dto.setReviewerId(r.getReviewer().getId());
//                    dto.setReviewerName(r.getReviewer().getUsername());
//                    dto.setReviewerEmail(r.getReviewer().getEmail());
//                    dto.setReviewerProfilePic(r.getReviewer().getProfilePicUrl());
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

    public UserProfileWithReviewsDto getUserWithProfileAndReviews(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Profile profile = user.getProfile();
        ProfileResponseDto profileDto = null;
        if (profile != null) {
            profileDto = new ProfileResponseDto();
            profileDto.setFullName(profile.getFullName());
            profileDto.setPhoneNumber(profile.getPhoneNumber());
            profileDto.setLocation(profile.getLocation());
            profileDto.setBio(profile.getBio());
            profileDto.setImage(profile.getImageUrl());
            profileDto.setWebsiteUrl(profile.getWebsiteUrl());
            profileDto.setSkills(profile.getSkills());
            profileDto.setSkillsYouWant(profile.getSkillsYouWant());
        }

        List<ReviewResponseDto> reviews = user.getReviewsReceived().stream()
                .map(r -> {
                    ReviewResponseDto dto = new ReviewResponseDto();
                    dto.setId(r.getId());
                    dto.setReviewText(r.getReview());
                    dto.setReviewerId(r.getReviewer().getId());
                    dto.setReviewerName(r.getReviewer().getUsername());
                    dto.setReviewerEmail(r.getReviewer().getEmail());
                    dto.setRating(reviewRepository.findAverageRatingForUser(userId));
                    dto.setReviewerProfilePic(r.getReviewer().getProfile().getImageUrl());
                    return dto;
                })
                .toList();

        UserProfileWithReviewsDto response = new UserProfileWithReviewsDto();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setProfile(profileDto);
        response.setReviewsReceived(reviews);

        return response;
    }


}

