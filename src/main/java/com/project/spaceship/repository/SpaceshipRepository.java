package com.project.spaceship.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.project.spaceship.model.Spaceship;

public interface SpaceshipRepository extends BaseRepository<Spaceship> {

	@Cacheable("spaceshipsByNameContaining")
	List<Spaceship> findByNameContainingIgnoreCase(String name);

	@Cacheable("spaceshipByName")
	Optional<Spaceship> findByName(String name);

	@Override
	@CacheEvict(value = { "spaceshipsByNameContaining", "spaceshipByName" }, key = "#result.id")
	Spaceship save(Spaceship entity);

	@Override
	@CacheEvict(value = { "spaceshipsByNameContaining", "spaceshipByName" }, key = "#id")
	void deleteById(Long id);

}
