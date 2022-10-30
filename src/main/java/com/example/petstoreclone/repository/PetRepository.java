package com.example.petstoreclone.repository;

import com.example.petstoreclone.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
