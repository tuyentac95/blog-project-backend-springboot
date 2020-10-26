package com.codegym.repository;

import com.codegym.dto.PostResponse;
import com.codegym.model.Post;
import com.codegym.model.SubBlog;
import com.codegym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubBlog(SubBlog subBlog);

    List<Post> findByUser(User user);
}
