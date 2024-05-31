package com.project.spaceship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
