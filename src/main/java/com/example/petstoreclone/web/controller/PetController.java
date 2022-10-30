package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.entity.User;
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
}
