package com.project.spaceship.mapper;

import java.util.List;

import com.project.spaceship.dto.BaseDto;
import com.project.spaceship.model.BaseEntity;

public interface BaseMapper<T extends BaseEntity,D extends BaseDto> {

	T dtoToEntity(D dto);
	D entityToDto(T entity);
	
	List<D> entityToDto(Iterable<T> entityIterable );
	List<T> dtoToEntity(Iterable<D> dtoIterable );
}
