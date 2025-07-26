package com.example.Matches.generic.service;

import com.example.Matches.generic.model.BaseEntity;
import com.example.Matches.generic.payload.request.GenericIdsDto;
import com.example.Matches.generic.payload.request.IDto;
import com.example.Matches.generic.payload.request.SDto;
import com.example.Matches.generic.payload.response.BaseResponseDto;
import com.example.Matches.generic.payload.response.PageData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface IService<E extends BaseEntity, D extends IDto, S extends SDto> {

    E create(D d) throws IOException;

    E update(D d, Long id) throws IOException;

    <T extends BaseResponseDto> T getSingle(Long id);

    E findById(Long id);

    E updateActiveStatus(Long id, Boolean b);

    E saveItem(E entity);

    List<E> saveItemList(List<E> entityList);

    List<E> findAllByIds(Collection<Long> ids);

    default void validateClientData(D d, E entity) {
    }

    ResponseEntity<List<Object>> getAll();


    PageData search(S dto, Pageable pageable);

    List<E> batchActiveStatus(GenericIdsDto ids);
}