package com.example.petstoreclone.service;

import com.example.petstoreclone.entity.Pet;
import com.example.petstoreclone.exceptions.NoIdFoundException;
import com.example.petstoreclone.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Pet update(Pet pet) {
        return petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    public List<Pet> findPetsByStatus(String status) {
        return petRepository.findPetsByStatus(status);
    }

    @Transactional(readOnly = true)
    public Optional<Pet> findById(long id) {
        return petRepository.findById(id);
    }

    public Pet deleteById(long id) {
        Optional<Pet> byId = findById(id);
        if (byId.isPresent()) {
            petRepository.deleteById(id);
            return byId.get();
        } else throw new NoIdFoundException();
    }
}
