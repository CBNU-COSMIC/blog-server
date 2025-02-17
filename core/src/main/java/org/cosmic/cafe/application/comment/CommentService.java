package org.cosmic.cafe.application.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.comment.dto.CommentResponseDTO;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.cosmic.cafe.domain.Comment.exception.CommentErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public UUID saveComment(UUID userId, String content, UUID postId, UUID parentId) {

        Comment comment = getComment(userId, content, postId, parentId);

        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void deleteComment(String stringCommentId, UUID memberId) {
        UUID commentId = UUID.fromString(stringCommentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        comment.ifPresent(comment1 -> comment1.validateOwner(memberId));
        commentRepository.deleteById(memberId);
    }

    @Transactional
    public CommentResponseDTO modifyComment(String content, String commentId, UUID memberId) {
        Comment comment = commentRepository.findById(UUID.fromString(commentId))
                .orElseThrow(() -> new BadRequestException("해당 댓글이 존재하지 않습니다.", CommentErrorCode.NO_SUCH_COMMENT));
        comment.modifyContent(memberId, content);
        return new CommentResponseDTO(comment);
    }

    public List<CommentResponseDTO> getCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(UUID.fromString(postId));
        return comments.stream().map(CommentResponseDTO::new).toList();
    }

    private static Comment getComment(UUID userId, String content, UUID postId, UUID parentId) {
        Comment comment = Comment.builder()
                .content(content)
                .postId(postId)
                .parentId(parentId)
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .build();
        return comment;
    }
}
