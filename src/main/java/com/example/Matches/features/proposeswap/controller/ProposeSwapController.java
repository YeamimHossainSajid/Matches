package com.example.Matches.features.proposeswap.controller;

import com.example.Matches.auth.model.User;
import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.response.ProposeSwapResponseDto;
import com.example.Matches.features.proposeswap.service.ProposeSwapService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("Propose_Swap")
public class ProposeSwapController {

    ProposeSwapService proposeSwapService;
    public ProposeSwapController(ProposeSwapService proposeSwapService) {
     this.proposeSwapService = proposeSwapService;
    }
    @PostMapping("/send")
    public ResponseEntity<String> sendSwap(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String yourOffer,
            @RequestParam String wantInReturn,
            @RequestParam String swapDetails,
            @RequestParam String swapDuration,
            @RequestParam String associatedDeposit) {

                proposeSwapService.sendSwap(senderId, receiverId, yourOffer, wantInReturn,
                        swapDetails, swapDuration, associatedDeposit);

                return ResponseEntity.ok("success");
    }

    @PostMapping("/{swapId}/respond")
    public ResponseEntity<String> respondToSwap(
            @PathVariable Long swapId,
            @RequestParam RequestStatus status) {

   proposeSwapService.respondToSwap(swapId, status);
   return ResponseEntity.ok("success");

    }

    @GetMapping("/pending/{receiverId}")
    public ResponseEntity<List<ProposeSwapResponseDto>> getPendingSwaps(@PathVariable Long receiverId) {
        return ResponseEntity.ok(proposeSwapService.getPendingSwaps(receiverId));
    }

//    @GetMapping("/by-user/{userId}")
//    public List<ProposeSwap> getSwapsByUserAndStatus(
//            @PathVariable Long userId,
//            @RequestParam List<RequestStatus> statuses
//    ) {
//        return proposeSwapService.findByUserAndStatus(statuses, userId);
//    }

    @GetMapping("/count/{userId}")
    public Map<String, Long> getSwapCountsByUser(@PathVariable Long userId) {
        return proposeSwapService.countSwapsByUser(userId);
    }
    @GetMapping("/grouped")
    public ResponseEntity<Map<RequestStatus, List<ProposeSwapResponseDto>>> getSwapsGroupedByStatus(
            @RequestParam Long userId,
            @RequestParam List<RequestStatus> statuses
    ){

        Map<RequestStatus, List<ProposeSwapResponseDto>> result =
                proposeSwapService.getSwapsGroupedByStatus(userId, statuses);

        return ResponseEntity.ok(result);
    }
}
