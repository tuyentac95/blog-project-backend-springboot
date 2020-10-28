package com.codegym.service;

import com.codegym.dto.CommentsDto;
import com.codegym.exception.BlogException;
import com.codegym.mapper.CommentMapper;
import com.codegym.model.Comment;
import com.codegym.model.NotificationEmail;
import com.codegym.model.Post;
import com.codegym.model.User;
import com.codegym.repository.CommentRepository;
import com.codegym.repository.PostRepository;
import com.codegym.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    private final MailContentBuilder mailContentBuilder;

    private final MailService mailService;

    public void save(CommentsDto commentsDto){
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new BlogException("Post Not Found: " + commentsDto.getPostId()));

        System.out.println("Current User: " + authService.getCurrentUser().getUsername());

        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

//        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post.");
//        sendCommentNotification(message, post.getUser());
    }

//    private void sendCommentNotification(String message, User user) {
//        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
//    }


    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BlogException("Post Not Found: " + postId));
        return commentRepository.findByPost(post).stream().map(commentMapper::mapToDto).collect(Collectors.toList());
    }
}
