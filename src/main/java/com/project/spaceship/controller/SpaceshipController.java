package com.project.spaceship.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.spaceship.dto.SpaceshipDto;
import com.project.spaceship.service.SpaceshipService;

@RestController
@RequestMapping("/api/v1/spaceships")
public class SpaceshipController {
	
	@Autowired
    private SpaceshipService spaceshipService;

	@GetMapping("/pages")
    public ResponseEntity<List<SpaceshipDto>> getPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
		List<SpaceshipDto> spaceshipsPage = this.spaceshipService.findAll(page, size);
        return new ResponseEntity<>(spaceshipsPage, HttpStatus.OK);
    }
	
    @GetMapping("/all")
    public ResponseEntity getAll() {
        return new ResponseEntity(this.spaceshipService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SpaceshipDto> getById(@PathVariable Long id) {
    	SpaceshipDto s = this.spaceshipService.findById(id);
    	if ( s != null) {
    		return new ResponseEntity<>(s, HttpStatus.OK) ;
    	}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpaceshipDto>> searchByName(@RequestParam String name) {
        return new ResponseEntity<>(this.spaceshipService.findByNameContainingIgnoreCase(name), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<SpaceshipDto> create(@RequestBody SpaceshipDto spaceship) {
        this.spaceshipService.save(spaceship);
        return new ResponseEntity<>(spaceship, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceshipDto> update(@PathVariable Long id, @RequestBody SpaceshipDto spaceshipDetails) {
    	SpaceshipDto spaceship = this.spaceshipService.findById(id);
        if (spaceship != null) {
            spaceship.setName(spaceshipDetails.getName());
            return new ResponseEntity<>(this.spaceshipService.save(spaceship), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    	this.spaceshipService.deleteById(id);
    }

}
