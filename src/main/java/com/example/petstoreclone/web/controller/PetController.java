package com.example.petstoreclone.web.controller;

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

//    @GetMapping("/findByStatus")
//    @Operation(summary = "Find pets by status")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "400", description = "Invalid status value"),
//            @ApiResponse(responseCode = "200", description = "OK"),
//    })
//    public ResponseEntity<?> findByStatus(@Parameter(description = "Status values that need to be considered for filter") String status) {
//        List<Pet> allByStatus = petService.findAllByStatus(status);
//        System.out.println(allByStatus);
//        return new ResponseEntity<>(allByStatus, HttpStatus.OK);
//    }

    @GetMapping("/{petId}")
    @Operation(summary = "Find pet by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID value"),
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    public ResponseEntity<?> findById(@PathVariable("petId") @Parameter(description = "Status values that need to be considered for filter") long id) {
        Optional<Pet> byId = petService.findById(id);
        if (byId.isPresent()) {
            return new ResponseEntity<>(byId.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
