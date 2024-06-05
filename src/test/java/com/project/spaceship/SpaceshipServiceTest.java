package com.project.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.spaceship.dto.SpaceshipDto;
import com.project.spaceship.service.SpaceshipService;

@SpringBootTest
class SpaceshipServiceTest {
	
	@Autowired
    private SpaceshipService spaceshipService;
	
	@BeforeEach
    public void setUp() {
		SpaceshipDto s = new SpaceshipDto();
		s.setName("Test");
		this.spaceshipService.save(s);
	}
	
	@AfterEach
    public void cleanUp() {
        this.spaceshipService.deleteAll();
    }
	
	@Test
	public void testCreate() {
		List<SpaceshipDto> list = this.spaceshipService.findByNameContainingIgnoreCase("test");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testUpdate() {
		String oldName = "Test";
		String newName = "Update";
		
        SpaceshipDto s = this.spaceshipService.findByName(oldName);
    	s.setName(newName);
        this.spaceshipService.save(s);

        SpaceshipDto updatedSpaceship = this.spaceshipService.findByName(newName);
        assertEquals(updatedSpaceship.getName(), newName);
        
	}

	@Test
	public void testFindAll() {
		SpaceshipDto s = new SpaceshipDto();
		s.setName("Test2");
		this.spaceshipService.save(s);
		
		List<SpaceshipDto> list = this.spaceshipService.findAll(0,10);
		assertEquals(list.size(), 2);
	}

}
