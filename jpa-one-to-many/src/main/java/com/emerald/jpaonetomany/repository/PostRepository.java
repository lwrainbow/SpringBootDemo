package com.emerald.jpaonetomany.repository;

import com.emerald.jpaonetomany.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The PostRepository class offer function to access data from database
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
