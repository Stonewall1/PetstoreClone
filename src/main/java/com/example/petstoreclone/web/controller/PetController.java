package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
