package com.thirdparty.controllers;

import com.thirdparty.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

//    get all posts
    @GetMapping("/getAllPosts")
    public List<Map<String, Object>> getAllPosts(){
        List<Map<String, Object>> posts = postService.getPosts();
        return posts;
    }

//    get post by id
    @GetMapping("/getPostById/{id}")
    public Map<String, Object> getPostsById(@PathVariable int id){
        Map<String, Object> post = postService.getPostById(id);
        return post;
    }

//    save post
    @PostMapping("/posts")
    public Map<String, Object> insertPost(@RequestBody Map<String, Object> payload){
        Map<String, Object> post = postService.insertPosts(payload);
        return post;
    }

//    update post
    @PutMapping("/update/{id}")
    public Map<String, Object> updatePost(@RequestBody Map<String, Object> payload, @PathVariable int id){
        Map<String, Object> post = postService.updatePostById(payload, id);
        return post;
    }

//    delete post
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deletePost(@PathVariable int id){
        Map<String, Object> post = postService.deleteById(id);
        return post;
    }



}
