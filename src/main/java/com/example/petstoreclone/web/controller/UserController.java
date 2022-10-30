package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.entity.User;
import com.example.petstoreclone.service.UserService;
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
@RequestMapping("/user")
@Tag(name = "user", description = "Operations about user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create user", description = "This can only be done by the logged in user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "201", description = "Created")
    })
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User save = userService.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user by user name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "202", description = "Accepted"),
    })
    public ResponseEntity<User> findByUsername(@PathVariable("username") @Parameter(description = "Username that needs to be fetched") String username) {
        Optional<User> byUsername = userService.findByUsername(username);
        return byUsername.map(user -> new ResponseEntity<>(user, HttpStatus.ACCEPTED)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update user", description = "This can only be done by the logged in user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "202", description = "Accepted"),
    })
    public ResponseEntity<?> updateByUsername(@PathVariable("username") @Parameter(description = "Updating by username") String username, @Valid @RequestBody User user) {
        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername.isPresent()) {
            user.setId(byUsername.get().getId());
            User save = userService.save(user);
            return new ResponseEntity<>(save, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user", description = "This can only be done by the logged in user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid username supplied"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "202", description = "Accepted"),
    })
    public ResponseEntity<?> deleteByUsername(@PathVariable("username") String username) {
        Optional<User> user = userService.deleteByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
