package com.imaishika.blog.controller;

import com.imaishika.blog.payload.PostDto;
import com.imaishika.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create a new post rest api
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts rest api
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    //get post by {id}
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="postId") long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    //update a post by id
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable(name="postId") long postId) {
        return  ResponseEntity.ok(postService.updatePostById(postDto, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable(name="postId") long postId) {
        return ResponseEntity.ok(postService.deletePostById(postId));
    }
}
