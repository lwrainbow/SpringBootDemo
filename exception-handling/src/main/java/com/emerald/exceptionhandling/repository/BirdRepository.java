package com.emerald.exceptionhandling.repository;

import com.emerald.exceptionhandling.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Long> {
}
