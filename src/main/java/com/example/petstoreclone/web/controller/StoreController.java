package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.entity.Order;
import com.example.petstoreclone.service.OrderService;
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
@RequestMapping("/store")
@Tag(name = "store", description = "Access to petstore orders")
public class StoreController {
    private final OrderService orderService;

    public StoreController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    @Operation(summary = "Place an order for a pet", description = "Order placed for purchasing the pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid order"),
            @ApiResponse(responseCode = "201", description = "Created")
    })
    public ResponseEntity<?> create(@Valid @RequestBody Order order) {
        Order save = orderService.save(order);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    @Operation(summary = "Find purchase order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<?> findById(@PathVariable(name = "orderId") @Parameter(description = "ID of order that needs to be fetched") long orderId) {
        Optional<Order> byId = orderService.findById(orderId);
        if (byId.isPresent()) {
            return new ResponseEntity<>(byId, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
