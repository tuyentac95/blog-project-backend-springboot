package com.codegym.service;

import com.codegym.dto.PostRequest;
import com.codegym.dto.PostResponse;
import com.codegym.exception.BlogException;
import com.codegym.mapper.PostMapper;
import com.codegym.model.Post;
import com.codegym.model.SubBlog;
import com.codegym.model.User;
import com.codegym.repository.PostRepository;
import com.codegym.repository.SubBlogRepository;
import com.codegym.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubBlogRepository subBlogRepository;

    private final PostRepository postRepository;

    private AuthService authService;

    private final PostMapper postMapper;

    private final UserRepository userRepository;

    public Post save(PostRequest postRequest){
        System.out.println("check1 "+postRequest.getSubBlogName());
        SubBlog subBlog = subBlogRepository.findByName(postRequest.getSubBlogName())
                .orElseThrow(() -> new BlogException("SubBlog Not Found: " + postRequest.getSubBlogName()));

        System.out.println("check2 " + subBlog.getName());
        User currentUser = authService.getCurrentUser();

        System.out.println(currentUser.getUsername());
        return postRepository.save(postMapper.map(postRequest, subBlog, currentUser));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BlogException("Post Not Found: " + id));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubBlog(Long id){
        SubBlog subBlog = subBlogRepository.findById(id)
                .orElseThrow(() -> new BlogException("Sub Blog Not Found: " + id));
        List<Post> posts = postRepository.findAllBySubBlog(subBlog);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BlogException("Username not found: " + username));
        return postRepository.findByUser(user)
                .stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
}
