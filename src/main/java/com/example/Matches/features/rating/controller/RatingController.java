package com.example.Matches.features.rating.controller;
import com.example.Matches.features.rating.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("Rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }


    @PostMapping("/give")
    public ResponseEntity<String> giveRating(
            @RequestParam Long raterId,
            @RequestParam Long rateeId,
            @RequestParam int rating) {
       ratingService.giveOrUpdateRating(raterId, rateeId, rating);
       return ResponseEntity.ok("Rating gave successfully");
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
