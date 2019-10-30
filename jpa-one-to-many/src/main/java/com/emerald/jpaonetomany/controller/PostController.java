package com.emerald.jpaonetomany.controller;

import com.emerald.jpaonetomany.exception.ResourceNotFoundException;
import com.emerald.jpaonetomany.model.Post;
import com.emerald.jpaonetomany.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The PostController handle create, retrieve, update, and delete Posts
 */
@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;

    /**
     * Create a post
     * @param post post object
     * @return the post object just created
     */
    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }

    /**
     * Get all the post from database
     * @param pageable page information for the return value
     * @return all the post by required page
     */
    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    /**
     * Get a post by postId
     * @param postId ID of a post
     * @return a post or ResourceNotFoundException
     */
    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable(value = "id") Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    /**
     * Update a post
     * @param postId ID of a post
     * @param postRequest all the field that need to be updated
     * @return a post or ResourceNotFoundException
     */
    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

    /**
     * Delete a post includes all the comment in this post
     * @param postId ID of a post
     * @return the status of the deletion
     */
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}
