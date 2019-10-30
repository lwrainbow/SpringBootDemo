package com.emerald.jpaonetomany.repository;

import com.emerald.jpaonetomany.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The CommentRepository class offer function to access data from database
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId (Long postId, Pageable pageable);
    Optional<Comment> findByIdAndPostId (Long id, Long postId);
}
