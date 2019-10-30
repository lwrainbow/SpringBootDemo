package com.emerald.jpaonetomany.controller;

import com.emerald.jpaonetomany.exception.ResourceNotFoundException;
import com.emerald.jpaonetomany.model.Comment;
import com.emerald.jpaonetomany.repository.CommentRepository;
import com.emerald.jpaonetomany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The CommentController handle create, retrieve, update, and delete Comments
 */
@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * Create a comment for a post
     * @param postId ID of a post
     * @param comment A comment object
     * @return the comment object just created or ResourceNotFoundException for post
     */
    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId,
                                 @Valid @RequestBody Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    /**
     * Get all the comment in a post
     * @param postId ID of a post
     * @param pageable page information for the return value
     * @return all the comment by required page in a post
     */
    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    /**
     * Update a comment in a post
     * @param postId ID of a post
     * @param commentId ID of a comment
     * @param commentRequest all the field that need to be updated
     * @return a updated comment in a post or ResourceNotFoundException for a post or a comment
     */
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest) {

        // Find the post by postId. If not throw the exception
        if(!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    /**
     * Delete a comment in a post
     * @param postId ID of a post
     * @param commentId ID of a comment
     * @return the status of the deletion
     */
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
}
