package com.codegym.mapper;

import com.codegym.dto.AppBlogDto;
import com.codegym.model.Post;
import com.codegym.model.SubBlog;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubBlogMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subBlog.getPosts()))")
    AppBlogDto mapBlogToDto(SubBlog subBlog);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubBlog mapDtoToBlog(AppBlogDto appBlogDto);
}
