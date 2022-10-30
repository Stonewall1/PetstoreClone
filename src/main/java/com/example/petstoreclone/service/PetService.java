package com.example.petstoreclone.service;

import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet save(Pet pet) {
        petRepository.save(pet);
        return pet;
    }
}
