package com.codegym.repository;

import com.codegym.model.SubBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubBlogRepository extends JpaRepository<SubBlog, Long> {
}
