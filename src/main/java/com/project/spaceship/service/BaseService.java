package com.project.spaceship.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.spaceship.model.BaseEntity;
import com.project.spaceship.repository.BaseRepository;

public abstract class BaseService<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = -1946626951735980203L;

	@Autowired
	protected BaseRepository<T> baseRepository;

    public Optional<T> findById(Long id) {
        return this.baseRepository.findById(id);
    }
	
	public List<T> findAll() {
        return this.baseRepository.findAll();
    }

    public T save(T entity) {
        return this.baseRepository.save(entity);
    }

    public void deleteById(Long id) {
        this.baseRepository.deleteById(id);
    }

    public void deleteAll() {
        this.baseRepository.deleteAll();
    }
}
