package com.example.petstoreclone.dto;

import javax.validation.constraints.NotBlank;

public class PetStatusDto {
    @NotBlank(message = "Field cant be empty")
    private String status;

    public PetStatusDto() {
    }

    public PetStatusDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
