package org.cosmic.cafe.application.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public UUID saveComment(UUID userId, String content, UUID postId, UUID parentId) {

        Comment comment = Comment.builder()
                .content(content)
                .postId(postId)
                .parentId(parentId)
                .userId(userId)
                .build();

        return commentRepository.save(comment).getId();
    }

}
