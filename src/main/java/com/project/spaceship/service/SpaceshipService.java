package com.project.spaceship.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.repository.SpaceshipRepository;

@Service
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    public SpaceshipService(SpaceshipRepository spaceshipRepository) {
        this.spaceshipRepository = spaceshipRepository;
    }

    public Spaceship findById(Long id) {
        return this.spaceshipRepository.findById(id).orElse(null);
    }

    @Cacheable("spaceships")    
    public Page<Spaceship> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.spaceshipRepository.findAll(pageable);
    }

    public Spaceship save(Spaceship spaceship) {
        return this.spaceshipRepository.save(spaceship);
    }

    public void deleteById(Long id) {
        this.spaceshipRepository.deleteById(id);
    }
    
    public List<Spaceship> findByNameContainingIgnoreCase(String name) {
        return this.spaceshipRepository.findByNameContainingIgnoreCase(name);
    }
    
}
