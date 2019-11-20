package com.emerald.exceptionhandling.controller;

import com.emerald.exceptionhandling.model.Bird;
import com.emerald.exceptionhandling.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class BirdController {
    @Autowired
    BirdService birdService;

    @PostMapping("/birds")
    public Bird createBird(@Valid @RequestBody Bird bird) {
        return birdService.createBird(bird);
    }

    @GetMapping("/birds/{birdId}")
    public Bird getBirdById(@PathVariable("birdId") Long birdId) {
        return birdService.getBirdById(birdId);
    }

    @GetMapping("/birds")
    public Page<Bird> getAllBirds(Pageable pageable) {
        return birdService.getAllBirds(pageable);
    }
}
