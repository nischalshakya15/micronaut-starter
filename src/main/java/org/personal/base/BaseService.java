package org.personal.base;

import lombok.RequiredArgsConstructor;
import org.personal.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BaseService<E extends BaseEntity, D extends BaseDto> {

    private final BaseRepository<E> baseRepository;

    private final BaseMapper<E, D> baseMapper;

    public List<D> findAll() {
        return baseRepository
                .findAll()
                .stream()
                .map(baseMapper::toDto)
                .collect(Collectors.toList());
    }

    public D save(D dto) {
        E entity = baseRepository.save(baseMapper.toEntity(dto));
        return baseMapper.toDto(entity);
    }

    public D update(D dto) {
        E entity = baseRepository.update(baseMapper.toEntity(dto));
        return baseMapper.toDto(entity);
    }

    public void delete(D dto) {
        E entity = baseMapper.toEntity(dto);
        baseRepository.delete(entity);
    }

    public D findById(Long id) {
        return baseRepository
                .findById(id)
                .map(baseMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with given id not found"));
    }
}
