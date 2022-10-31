package com.example.petstoreclone.dto;

import com.example.petstoreclone.entity.PetStatus;

import javax.validation.constraints.NotBlank;

public class PetUpdateDto {
    @NotBlank(message = "Field cant be empty")
    private String name;

    private PetStatus status;

    public PetUpdateDto() {
    }

    public PetUpdateDto(String name, PetStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }
}
