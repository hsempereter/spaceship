package com.project.spaceship.repository;

import java.util.List;

import com.project.spaceship.model.Spaceship;

public interface SpaceshipRepository extends BaseRepository<Spaceship> {

	List<Spaceship> findByNameContainingIgnoreCase(String name);
	Spaceship findByName(String name);
	
}
