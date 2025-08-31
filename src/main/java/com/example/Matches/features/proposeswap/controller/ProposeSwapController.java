package com.example.Matches.features.proposeswap.controller;

import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.features.proposeswap.service.ProposeSwapService;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.apache.commons.math3.analysis.function.Abs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Propose_Swap")
public class ProposeSwapController {

    ProposeSwapService proposeSwapService;
    public ProposeSwapController(ProposeSwapService proposeSwapService) {
     this.proposeSwapService = proposeSwapService;
    }
    @PostMapping("/send")
    public ResponseEntity<ProposeSwap> sendSwap(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String yourOffer,
            @RequestParam String wantInReturn,
            @RequestParam String swapDetails,
            @RequestParam String swapDuration,
            @RequestParam String associatedDeposit) {

        return ResponseEntity.ok(
                proposeSwapService.sendSwap(senderId, receiverId, yourOffer, wantInReturn,
                        swapDetails, swapDuration, associatedDeposit)
        );
    }

    @PostMapping("/{swapId}/respond")
    public ResponseEntity<ProposeSwap> respondToSwap(
            @PathVariable Long swapId,
            @RequestParam RequestStatus status) {

        return ResponseEntity.ok(proposeSwapService.respondToSwap(swapId, status));
    }

    @GetMapping("/pending/{receiverId}")
    public ResponseEntity<List<ProposeSwap>> getPendingSwaps(@PathVariable Long receiverId) {
        return ResponseEntity.ok(proposeSwapService.getPendingSwaps(receiverId));
    }
}
