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
import com.project.spaceship.repository.SpaceshipRepository;

@SpringBootTest
@ActiveProfiles("test")
class SpaceshipRepositoryTest {
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;
	
	@BeforeEach
    public void setUp() {
		Spaceship s = new Spaceship();
		s.setName("Test");
		this.spaceshipRepository.save(s);
	}
	
	@AfterEach
    public void cleanUp() {
        this.spaceshipRepository.deleteAll();
    }
	
	@Test
	public void testCreate() {
		List<Spaceship> list = this.spaceshipRepository.findByNameContainingIgnoreCase("test");
		assertEquals(list.size(), 1);
	}
	
	@Test
	public void testUpdate() {
		String oldName = "Test";
		String newName = "Update";
		
        Spaceship s = this.spaceshipRepository.findByName(oldName);
        s.setName(newName);
        this.spaceshipRepository.save(s);

        Spaceship updatedSpaceship = this.spaceshipRepository.findByName(newName);
        assertEquals(updatedSpaceship.getName(), newName);
	}

	@Test
	public void testFindAll() {
		Spaceship s = new Spaceship();
		s.setName("Test2");
		this.spaceshipRepository.save(s);
		
		List<Spaceship> list = this.spaceshipRepository.findAll();
		assertEquals(list.size(), 2);
	}

}
