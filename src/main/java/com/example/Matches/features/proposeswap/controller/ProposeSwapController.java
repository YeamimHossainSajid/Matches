package com.example.Matches.features.proposeswap.controller;

import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.generic.controller.AbstractController;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.service.IService;
import org.apache.commons.math3.analysis.function.Abs;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Propose_Swap")
public class ProposeSwapController extends AbstractController<ProposeSwap, ProposeSwapRequestDto, GenericSearchDto> {
    public ProposeSwapController(IService<ProposeSwap, ProposeSwapRequestDto, GenericSearchDto> service) {
        super(service);
    }
}
