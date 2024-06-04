package com.project.spaceship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.repository.SpaceshipRepository;

@Service
public class SpaceshipService extends BaseService<Spaceship> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;

    @Cacheable("spaceships")    
    public Page<Spaceship> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.spaceshipRepository.findAll(pageable);
    }
    
    public List<Spaceship> findByNameContainingIgnoreCase(String name) {
        return this.spaceshipRepository.findByNameContainingIgnoreCase(name);
    }
    
    public Optional<Spaceship> findByName(String name) {
        return this.spaceshipRepository.findByName(name);
    }
    
    
}
