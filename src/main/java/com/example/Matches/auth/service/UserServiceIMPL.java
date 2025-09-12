package com.example.Matches.auth.service;

import com.example.Matches.auth.dto.request.UserRequestDTO;
import com.example.Matches.auth.dto.request.UserRoleRequestDTO;
import com.example.Matches.auth.dto.response.CustomRoleResponseDTO;
import com.example.Matches.auth.dto.response.CustomUserResponseDTO;
import com.example.Matches.auth.dto.response.CustomUserResponseDtoCls;
import com.example.Matches.auth.model.Role;
import com.example.Matches.auth.model.User;
import com.example.Matches.auth.repository.RoleRepo;
import com.example.Matches.auth.repository.UserRepo;
import com.example.Matches.config.image.service.CloudneryImageService;
import com.example.Matches.config.mail.EmailService;
import com.example.Matches.config.mail.OtpService;
import com.example.Matches.features.profile.entity.Profile;
import com.example.Matches.features.profile.payload.response.ProfileResponseDto;
import com.example.Matches.features.profile.repository.ProfileRepository;
import com.example.Matches.features.review.payload.response.ReviewResponseDto;
import com.example.Matches.features.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepository;
    private final ProfileRepository profileRepository;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CloudneryImageService cloudneryImageService;

    @Autowired
    private ReviewRepository reviewRepository;

    private User tempUser;

    public UserServiceIMPL(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
    }

    public User ConvertToEntity(User user, UserRequestDTO userRequestDTO) {
        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        return user;
    }

    private CustomUserResponseDtoCls convertToResponseDtoCls(User user) {

        ProfileResponseDto profileDto = null;
        if (user.getProfile() != null) {
            profileDto = new ProfileResponseDto();
            profileDto.setId(user.getProfile().getId());
            profileDto.setFullName(user.getProfile().getFullName());
            profileDto.setPhoneNumber(user.getProfile().getPhoneNumber());
            profileDto.setLocation(user.getProfile().getLocation());
            profileDto.setBio(user.getProfile().getBio());
            profileDto.setImage(user.getProfile().getImageUrl()); // assuming entity field is imageUrl
            profileDto.setWebsiteUrl(user.getProfile().getWebsiteUrl());
            profileDto.setSkills(user.getProfile().getSkills());
            profileDto.setSkillsYouWant(user.getProfile().getSkillsYouWant());
        }


        List<ReviewResponseDto> reviewsReceived = user.getReviewsReceived().stream()
                .map(review -> {
                    ReviewResponseDto dto = new ReviewResponseDto();
                    dto.setId(review.getId());
                    dto.setReviewText(review.getReview()); // assuming Review entity field = comment
                    dto.setReviewerId(review.getReviewer().getId());
                    dto.setReviewerName(review.getReviewer().getUsername());
                    dto.setReviewerEmail(review.getReviewer().getEmail());
                    dto.setReviewerProfilePic(
                            review.getReviewer().getProfile() != null
                                    ? review.getReviewer().getProfile().getImageUrl()
                                    : null
                    );
                    return dto;
                })
                .toList();


        Set<CustomRoleResponseDTO> roles = user.getRoles().stream()
                .map(role -> new CustomRoleResponseDTO() {
                    @Override
                    public Long getId() {
                        return role.getId();
                    }

                    @Override
                    public String getRoleType() {
                        return role.getRoleType().toString();
                    }

                    @Override
                    public Set<UserInfo> getUsers() {
                        return null;
                    }
                })
                .collect(Collectors.toSet());



        CustomUserResponseDtoCls dto = new CustomUserResponseDtoCls();
        dto.setRating(reviewRepository.findAverageRatingForUser(user.getId()));
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(roles);
        dto.setReviewsReceived(reviewsReceived);
        dto.setProfile(profileDto);

        return dto;
    }



    public String create(UserRequestDTO requestDto) {
        if (userRepository.findByUsername(requestDto.username()) != null) {
            throw new RuntimeException("User already exists");
        }

        String generatedOtp = otpService.generateOtp(requestDto.email());
        emailService.sendOtpEmail(requestDto.email(), generatedOtp);

        tempUser = ConvertToEntity(new User(), requestDto);

        return "OTP sent to email. Please verify before proceeding.";
    }


    public String validateOtp(String email, String otp) {
        if (!otpService.verifyOtp(email, otp)) {
            return "Invalid OTP! Please try again.";
        }

        if (tempUser == null || !tempUser.getEmail().equals(email)) {
            return "No user data found. Please register again.";
        }

        userRepository.save(tempUser);
        otpService.removeOtp(email);
        tempUser = null;

        return "User registered successfully!";
    }


    public CustomUserResponseDTO readOne(Long id) {
        CustomUserResponseDTO singleUserById = userRepository.findUserByUserId(id);
        if (Objects.isNull(singleUserById)) {
            throw new RuntimeException("User with id " + id + " not found.");
        }
        return singleUserById;
    }


    public User setUserRoles(UserRoleRequestDTO requestDTO) {
        User foundUser = userRepository.findById(requestDTO.userId()).orElse(null);

        if (foundUser == null) {
            throw new RuntimeException("User with id " + requestDTO.userId() + " not found.");
        }

        Set<Role> foundRoles = roleRepository.findAllByIdIn(requestDTO.roleIds());
        foundUser.getRoles().addAll(foundRoles);

        return userRepository.save(foundUser);
    }

    @Override
    public void updateUser(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User with id " + id + " not found.")
        );

        User updateUser = ConvertToEntity(user, userRequestDTO);
        userRepository.save(updateUser);
    }

    @Override
    public CustomUserResponseDTO searchByUsername(String username) {
        return userRepository.searchByUsername(username);
    }


    public CustomUserResponseDtoCls getUserInfoById(Long userId) {
        User user = userRepository.findUserWithDetailsById(userId);
        if (user == null) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        return convertToResponseDtoCls(user);
    }

}
