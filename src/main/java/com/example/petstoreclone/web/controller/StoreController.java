package com.example.petstoreclone.web.controller;

import com.example.petstoreclone.entity.Order;
import com.example.petstoreclone.service.OrderService;
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
}
