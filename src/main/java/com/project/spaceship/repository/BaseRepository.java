package com.project.spaceship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.project.spaceship.model.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity>
		extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
	
}
