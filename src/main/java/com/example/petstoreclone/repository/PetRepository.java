package com.example.petstoreclone.repository;

import com.example.petstoreclone.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findPetsByStatus(String status);
}
