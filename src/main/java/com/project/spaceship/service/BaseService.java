package com.project.spaceship.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spaceship.dto.BaseDto;
import com.project.spaceship.mapper.BaseMapper;
import com.project.spaceship.model.BaseEntity;
import com.project.spaceship.repository.BaseRepository;

@Service
public abstract class BaseService<T extends BaseEntity, D extends BaseDto> implements Serializable {

	private static final long serialVersionUID = -1946626951735980203L;

	@Autowired
	protected BaseRepository<T> baseRepository;
	
	@Autowired
	protected BaseMapper<T, D> baseMapper;

    public D findById(Long id) {
    	if ( this.baseRepository.findById(id).isPresent())
    		return this.baseMapper.entityToDto(this.baseRepository.findById(id).get()); 
    	return null;
    }
    
    public List<D> findAll() {
        return this.baseMapper.entityToDto(this.baseRepository.findAll());
    }

    public D save(D dto) {
        return this.baseMapper.entityToDto(this.baseRepository.save(this.baseMapper.dtoToEntity(dto)));
    }

    public void deleteById(Long id) {
        this.baseRepository.deleteById(id);
    }

    public void deleteAll() {
        this.baseRepository.deleteAll();
    }
}
