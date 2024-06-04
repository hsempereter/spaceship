package com.project.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.service.SpaceshipService;

@SpringBootTest
class SpaceshipServiceTest {
	
	@Autowired
    private SpaceshipService spaceshipService;
	
	@BeforeEach
    public void setUp() {
		Spaceship s = new Spaceship();
		s.setName("Test");
		this.spaceshipService.save(s);
	}
	
	@AfterEach
    public void cleanUp() {
        this.spaceshipService.deleteAll();
    }
	
	@Test
	public void testCreate() {
		List<Spaceship> list = this.spaceshipService.findByNameContainingIgnoreCase("test");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testUpdate() {
		String oldName = "Test";
		String newName = "Update";
		
        Optional<Spaceship> snapship = this.spaceshipService.findByName(oldName);
        if ( snapship.isPresent() ) {
        	Spaceship s = snapship.get();
        	s.setName(newName);
            this.spaceshipService.save(s);

            Optional<Spaceship> updatedSpaceship = this.spaceshipService.findByName(newName);
            assertEquals(updatedSpaceship.isPresent() ? updatedSpaceship.get().getName() : "", newName);
        } else {
        	assertTrue(false);
        }
        
	}

	@Test
	public void testFindAll() {
		Spaceship s = new Spaceship();
		s.setName("Test2");
		this.spaceshipService.save(s);
		
		List<Spaceship> list = this.spaceshipService.findAll(0,10).toList();
		assertEquals(list.size(), 2);
	}

}
