package com.project.spaceship.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Spaceship extends BaseEntity {
    
	private static final long serialVersionUID = 1L;
	
	private String name;
}
