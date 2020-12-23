package org.personal.base;

import io.micronaut.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
