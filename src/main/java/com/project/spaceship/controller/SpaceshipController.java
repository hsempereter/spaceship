package com.project.spaceship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.repository.SpaceshipRepository;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;

    @GetMapping
    public List<Spaceship> getAll() {
        return this.spaceshipRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Spaceship getById(@PathVariable Long id) {
        return this.spaceshipRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Spaceship> searchByName(@RequestParam String name) {
        return this.spaceshipRepository.findByNameContainingIgnoreCase(name);
    }
    
    @PostMapping
    public Spaceship create(@RequestBody Spaceship spaceship) {
        return this.spaceshipRepository.save(spaceship);
    }

    @PutMapping("/{id}")
    public Spaceship update(@PathVariable Long id, @RequestBody Spaceship spaceshipDetails) {
        Spaceship spaceship = this.spaceshipRepository.findById(id).orElse(null);
        if (spaceship != null) {
            spaceship.setName(spaceshipDetails.getName());
            return this.spaceshipRepository.save(spaceship);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	this.spaceshipRepository.deleteById(id);
    }

}
