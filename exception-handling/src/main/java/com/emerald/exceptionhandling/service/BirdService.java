package com.emerald.exceptionhandling.service;

import com.emerald.exceptionhandling.model.Bird;
import com.emerald.exceptionhandling.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirdService {
    @Autowired
    private BirdRepository birdRepository;

    public Bird createBird(Bird bird) {
        return birdRepository.save(bird);
    }

    public Bird getBirdById(Long birdId) {
        return birdRepository.findById(birdId).orElseThrow();
    }

    public Page<Bird> getAllBirds(Pageable pageable) {
        return birdRepository.findAll(pageable);
    }
}
