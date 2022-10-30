package com.example.petstoreclone.dto;

import javax.validation.constraints.NotBlank;

public class PetUpdateDto {
    @NotBlank(message = "Field cant be empty")
    private String name;
    @NotBlank(message = "Field cant be empty")
    private String status;

    public PetUpdateDto() {
    }

    public PetUpdateDto(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
