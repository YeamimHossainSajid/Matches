package com.example.Matches.features.proposeswap.controller;

import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.entity.RequestStatus;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.features.proposeswap.payload.response.ProposeSwapResponseDto;
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
}
