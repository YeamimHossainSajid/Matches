package com.example.Matches.features.proposeswap.service.impl;

import com.example.Matches.features.proposeswap.entity.ProposeSwap;
import com.example.Matches.features.proposeswap.payload.request.ProposeSwapRequestDto;
import com.example.Matches.features.proposeswap.service.ProposeSwapService;
import com.example.Matches.generic.payload.request.GenericSearchDto;
import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.example.Matches.generic.repository.AbstractRepository;
import com.example.Matches.generic.service.AbstractService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProposeSwapServiceImpl extends AbstractService<ProposeSwap, ProposeSwapRequestDto, GenericSearchDto> implements ProposeSwapService {


    public ProposeSwapServiceImpl(AbstractRepository<ProposeSwap> repository) {
        super(repository);
    }

    @Override
    protected <T extends BaseResponseDto> T convertToResponseDto(ProposeSwap proposeSwap) {
        return null;
    }

    @Override
    protected ProposeSwap convertToEntity(ProposeSwapRequestDto proposeSwapRequestDto) throws IOException {
        return null;
    }

    @Override
    protected ProposeSwap updateEntity(ProposeSwapRequestDto proposeSwapRequestDto, ProposeSwap entity) throws IOException {
        return null;
    }

    @Override
    protected Specification<ProposeSwap> buildSpecification(GenericSearchDto searchDto) {
        return null;
    }
}
