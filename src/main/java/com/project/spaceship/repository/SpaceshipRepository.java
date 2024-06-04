package com.project.spaceship.repository;

import java.util.List;
import java.util.Optional;

import com.project.spaceship.model.Spaceship;

public interface SpaceshipRepository extends BaseRepository<Spaceship> {

	List<Spaceship> findByNameContainingIgnoreCase(String name);
	Optional<Spaceship> findByName(String name);
	
}
