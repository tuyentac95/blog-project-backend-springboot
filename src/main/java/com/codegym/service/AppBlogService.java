package com.codegym.service;

import com.codegym.dto.AppBlogDto;
import com.codegym.mapper.SubBlogMapper;
import com.codegym.model.SubBlog;
import com.codegym.repository.SubBlogRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AppBlogService {

    private final SubBlogRepository subBlogRepository;
    private final SubBlogMapper subBlogMapper;

    @Transactional
    public AppBlogDto save(AppBlogDto appBlogDto){
        SubBlog save = subBlogRepository.save(subBlogMapper.mapDtoToBlog(appBlogDto));
        appBlogDto.setId(save.getId());
        return appBlogDto;
    }

    @Transactional(readOnly = true)
    public List<AppBlogDto> getAll() {
        return subBlogRepository.findAll()
                .stream()
                .map(subBlogMapper::mapBlogToDto)
                .collect(Collectors.toList());
    }

//    private AppBlogDto mapToDto(SubBlog subBlog) {
//        return AppBlogDto.builder().name(subBlog.getName())
//                .id(subBlog.getId())
//                .numberOfPosts(subBlog.getPosts().size())
//                .build();
//    }
//
//    private SubBlog mapAppBlogDto(AppBlogDto appBlogDto) {
//        return SubBlog.builder().name(appBlogDto.getName())
//                .description(appBlogDto.getDescription())
//                .build();
//    }


}
