package com.project.spaceship.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.spaceship.dto.SpaceshipDto;
import com.project.spaceship.mapper.SpaceshipMapper;
import com.project.spaceship.model.Spaceship;
import com.project.spaceship.repository.SpaceshipRepository;

@Service
public class SpaceshipService extends BaseService<Spaceship, SpaceshipDto> {

	private static final long serialVersionUID = 1L;

	@Autowired
	SpaceshipMapper spaceshipMapper;

	@Autowired
	private SpaceshipRepository spaceshipRepository;

	public List<SpaceshipDto> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return this.spaceshipMapper.entityToDto(this.spaceshipRepository.findAll(pageable));
	}

	public List<SpaceshipDto> findByNameContainingIgnoreCase(String name) {
		return this.spaceshipMapper.entityToDto(this.spaceshipRepository.findByNameContainingIgnoreCase(name));
	}

	public SpaceshipDto findByName(String name) {
		Optional<Spaceship> ent = this.spaceshipRepository.findByName(name);
		return ent.isPresent() ? this.spaceshipMapper.entityToDto(ent.get()) : new SpaceshipDto();
	}

}
