package com.project.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.spaceship.model.Spaceship;
import com.project.spaceship.repository.SpaceshipRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)                         
class SpaceshipRepositoryIntegrationTest {
	
	@Mock
    private SpaceshipRepository spaceshipService;

    @Test
    public void testGetAllSpaceships() {
        Spaceship s = new Spaceship();
        s.setName("test");
    	
        List<Spaceship> expectedList = Arrays.asList(s);
        when(this.spaceshipService.findAll()).thenReturn(expectedList);

        List<Spaceship> list = this.spaceshipService.findAll();

        assertEquals(expectedList.size(), list.size());
        assertEquals(expectedList.get(0).getName(), list.get(0).getName());
    }

}
