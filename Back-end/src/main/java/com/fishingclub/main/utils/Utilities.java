package com.fishingclub.main.utils;

import com.fishingclub.main.exceptions.ResourceBadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public class Utilities<T, C, I> {
    private final ModelMapper modelMapper;

    private final JpaRepository<C, I> repository;


    public Utilities(ModelMapper modelMapper, JpaRepository<C, I> repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    public static Pageable managePagination(int page, int pageSize, String sortBy, String sortOrder) {
        if (!sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) && !sortOrder.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            throw new ResourceBadRequestException("Please make sure to choose either ascending or descending order.");
        }

        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return PageRequest.of(page, pageSize, sort);
    }

    public Page<T> getAllContents(Map<String, Object> params, Class<T> targetClass) {
        Pageable pageable = Utilities.managePagination((Integer) params.get("page"), (Integer) params.get("size"), (String) params.get("sortBy"), (String) params.get("sortOrder"));

        Page<C> contents = repository.findAll(pageable);

        return contents.map(c -> modelMapper.map(c, targetClass));
    }
}
