package com.webpage.book.springboot.web;

import com.webpage.book.springboot.service.posts.PostsService;
import com.webpage.book.springboot.service.posts.PostsService2;
import com.webpage.book.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController2 {

    private final PostsService2 postsService2;

    @PostMapping("/api/v1/posts2")
    public Long save(@RequestBody PostsSaveRequestDto2 requestDto2) {
        return postsService2.save(requestDto2);
    }

    @PutMapping("/api/v1/posts2/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto2 requestDto2) {
        return postsService2.update(id, requestDto2);
    }

    @GetMapping("/api/v1/posts2/{id}")
    public PostsResponseDto2 findById(@PathVariable Long id) {
        return postsService2.findById(id);

    }

    @DeleteMapping("/api/v1/posts2/{id}")
    public Long delete(@PathVariable Long id){
        postsService2.delete(id);
        return id;
    }
}