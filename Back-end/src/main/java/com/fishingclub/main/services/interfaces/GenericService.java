package com.fishingclub.main.services.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface GenericService<T, C, I> {
    T create(C t);
    T update(C t);
    T delete(I id);
    T getOne(I id);
    Page<T> getAll(Map<String, Object> params);
}
