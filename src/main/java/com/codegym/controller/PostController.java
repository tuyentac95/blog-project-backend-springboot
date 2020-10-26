package com.codegym.controller;

import com.codegym.dto.PostRequest;
import com.codegym.dto.PostResponse;
import com.codegym.model.Post;
import com.codegym.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest){
        Post post = postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
    }

    @GetMapping("/by-subblog/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubBlog(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubBlog(id));
    }

    @GetMapping("/by-user/{name}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUsername(name));
    };

}
