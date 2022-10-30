package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.dto.PetStatusDto;
import com.example.petstoreclone.dto.PetUpdateDto;
import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
@Tag(name = "pet", description = "Everything about your pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    @Operation(summary = "Add a new pet to the store")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "201", description = "Created")
    })
    public ResponseEntity<?> add(@Valid @RequestBody Pet pet) {
        Pet save = petService.save(pet);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update an existing pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "202", description = "Accepted"),
    })
    public ResponseEntity<?> update(@Valid @RequestBody Pet pet) {
        Pet update = petService.update(pet);
        return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
    }

    @GetMapping("/findByStatus")
    @Operation(summary = "Find pets by status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public ResponseEntity<List<Pet>> findByStatus(@Parameter(description = "Status values that need to be considered for filter") @Valid @RequestBody PetStatusDto status) {
        List<Pet> allByStatus = petService.findPetsByStatus(status.getStatus());
        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
    }

    @GetMapping("/{petId}")
    @Operation(summary = "Find pet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID value"),
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public ResponseEntity<?> findById(@PathVariable("petId") @Parameter(description = "Pet id") long id) {
        Optional<Pet> byId = petService.findById(id);
        if (byId.isPresent()) {
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{petId}")
    @Operation(summary = "Update pet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID value"),
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public ResponseEntity<?> update(@PathVariable("petId") long petId, @Valid @RequestBody PetUpdateDto petUpdateDto) {
        Optional<Pet> byId = petService.findById(petId);
        if (byId.isPresent()) {
            byId.get().setName(petUpdateDto.getName());
            byId.get().setStatus(petUpdateDto.getStatus());
            Pet update = petService.update(byId.get());
            return new ResponseEntity<>(update, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{petId}")
    @Operation(summary = "Deletes a pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID value"),
            @ApiResponse(responseCode = "202", description = "Accepted"),
    })
    public ResponseEntity<?> deleteById(@PathVariable("petId") @Parameter(description = "Pet id") long id) {
        Pet pet = petService.deleteById(id);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
}
