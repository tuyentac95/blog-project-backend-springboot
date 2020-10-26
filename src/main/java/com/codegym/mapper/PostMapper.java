package com.codegym.mapper;

import com.codegym.dto.PostRequest;
import com.codegym.dto.PostResponse;
import com.codegym.model.Post;
import com.codegym.model.SubBlog;
import com.codegym.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subBlog", source = "subBlog")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    Post map(PostRequest postRequest, SubBlog subBlog, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subBlogName", source = "subBlog.name")
    @Mapping(target = "username", source = "user.username")
//    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
//    @Mapping(target = "duration", expression = "java(getDuration(post))")
//    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
//    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    PostResponse mapToDto(Post post);
}
