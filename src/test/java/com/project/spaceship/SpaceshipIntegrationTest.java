package com.project.spaceship;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.project.spaceship.controller.SpaceshipController;
import com.project.spaceship.dto.SpaceshipDto;
import com.project.spaceship.service.SpaceshipService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SpaceshipController.class)
class SpaceshipIntegrationTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceshipService spaceshipService;

    @Test
    @WithMockUser(username="user", roles={"USER"})
    public void testGetAllSpaceships() throws Exception {
        SpaceshipDto s = new SpaceshipDto();
        s.setName("test");
    	
        List<SpaceshipDto> expectedList = Arrays.asList(s);
        when(this.spaceshipService.findAll()).thenReturn(expectedList);

        List<SpaceshipDto> list = this.spaceshipService.findAll();

        assertEquals(expectedList.size(), list.size());
        assertEquals(expectedList.get(0).getName(), list.get(0).getName());
        
        this.mockMvc.perform(get("/api/v1/spaceships/all")
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("test")));
    }

}
