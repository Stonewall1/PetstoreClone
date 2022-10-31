package com.example.petstoreclone.dto;

import com.example.petstoreclone.entity.PetStatus;

public class PetStatusDto {

    private PetStatus status;

    public PetStatusDto() {
    }

    public PetStatusDto(PetStatus status) {
        this.status = status;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }
}
