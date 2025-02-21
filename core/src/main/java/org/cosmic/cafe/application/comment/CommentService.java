package org.cosmic.cafe.application.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.comment.dto.CommentResponseDTO;
import org.cosmic.cafe.application.member.MemberService;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.cosmic.cafe.domain.Comment.exception.CommentErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberService memberService;

    public UUID saveComment(UUID userId, String content, UUID postId, UUID parentId) {

        Comment comment = createComment(userId, content, postId, parentId);

        return commentRepository.save(comment).getId();
    }

    public void deleteComment(UUID commentId, UUID memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException("해당 댓글이 존재하지 않습니다.", CommentErrorCode.NO_SUCH_COMMENT));
        comment.validateOwner(memberId);
        commentRepository.deleteById(commentId);
    }

    public CommentResponseDTO modifyComment(String content, UUID commentId, UUID memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException("해당 댓글이 존재하지 않습니다.", CommentErrorCode.NO_SUCH_COMMENT));
        Comment modifiedContent = comment.modifyContent(memberId, content);
        commentRepository.save(modifiedContent);
        return new CommentResponseDTO(modifiedContent, memberService.findById(modifiedContent.getUserId()));
    }

    public List<CommentResponseDTO> getCommentsByPostId(UUID postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> new CommentResponseDTO(comment, memberService.findById(comment.getUserId()))).toList();
    }

    public CommentResponseDTO getCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BadRequestException("해당 댓글이 존재하지 않습니다.", CommentErrorCode.NO_SUCH_COMMENT));
        return new CommentResponseDTO(comment, memberService.findById(comment.getUserId()));
    }

    public List<CommentResponseDTO> getCommentByParentId(UUID parentId) {
        List<Comment> comments = commentRepository.findByParentId(parentId);
        return comments.stream().map(comment -> new CommentResponseDTO(comment, memberService.findById(comment.getUserId()))).toList();
    }

    private static Comment createComment(UUID userId, String content, UUID postId, UUID parentId) {
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
