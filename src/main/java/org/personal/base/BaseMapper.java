package org.personal.base;

import io.micronaut.data.model.Page;

import java.util.List;

public interface BaseMapper<E, D> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(Iterable<D> dtoList);

    List<D> toDto(Iterable<E> entityList);

    List<D> toDto(Page<E> entityList);
}
