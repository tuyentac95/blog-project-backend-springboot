package com.codegym.mapper;

import com.codegym.dto.PostRequest;
import com.codegym.dto.PostResponse;
import com.codegym.model.Post;
import com.codegym.model.SubBlog;
import com.codegym.model.User;
import com.codegym.repository.CommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subBlog", source = "subBlog")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, SubBlog subBlog, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subBlogName", source = "subBlog.name")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
//    @Mapping(target = "duration", expression = "java(getDuration(post))")
//    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
//    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }
}
