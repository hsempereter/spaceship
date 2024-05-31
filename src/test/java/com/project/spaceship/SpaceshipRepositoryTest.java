package com.project.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.service.SpaceshipService;

@SpringBootTest
@ActiveProfiles("test")
class SpaceshipRepositoryTest {
	
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
		
        Spaceship s = this.spaceshipService.findByName(oldName);
        s.setName(newName);
        this.spaceshipService.save(s);

        Spaceship updatedSpaceship = this.spaceshipService.findByName(newName);
        assertEquals(updatedSpaceship.getName(), newName);
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
