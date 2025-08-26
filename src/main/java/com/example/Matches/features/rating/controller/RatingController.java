package com.example.Matches.features.rating.controller;

import com.example.Matches.features.rating.entity.Rating;
import com.example.Matches.features.rating.payload.request.RatingRequestDto;
import com.example.Matches.features.rating.service.RatingService;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("Rating")
public class RatingController extends AbstractController<Rating, RatingRequestDto, GenericSearchDto> {

    private final RatingService ratingService;

    public RatingController(IService<Rating, RatingRequestDto, GenericSearchDto> service, RatingService ratingService) {

        super(service);
        this.ratingService = ratingService;
    }


    @PostMapping("/give")
    public ResponseEntity<Rating> giveRating(
            @RequestParam Long raterId,
            @RequestParam Long rateeId,
            @RequestParam int rating) {
        return ResponseEntity.ok(ratingService.giveOrUpdateRating(raterId, rateeId, rating));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeRating(
            @RequestParam Long raterId,
            @RequestParam Long rateeId) {
        ratingService.removeRating(raterId, rateeId);
        return ResponseEntity.ok("Rating removed successfully");
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getAverageRating(@RequestParam Long userId) {
        return ResponseEntity.ok(ratingService.getAverageRating(userId));
    }

}
