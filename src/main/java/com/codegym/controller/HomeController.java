package com.codegym.controller;

import com.codegym.dto.AppBlogDto;
import com.codegym.service.AppBlogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appblog")
@AllArgsConstructor
@Slf4j
public class HomeController {

    private final AppBlogService appBlogService;

    @PostMapping
    public ResponseEntity<AppBlogDto> createAppBlog(@RequestBody AppBlogDto appBlogDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appBlogService.save(appBlogDto));
    }

    @GetMapping
    public ResponseEntity<List<AppBlogDto>> getAllSubBlogs(){
        return ResponseEntity.status(HttpStatus.OK).body(appBlogService.getAll());
    }
}
