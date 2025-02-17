package org.cosmic.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.comment.CommentService;
import org.cosmic.cafe.application.comment.dto.CommentResponseDTO;
import org.cosmic.cafe.application.comment.dto.ModifyCommentDTO;
import org.cosmic.cafe.application.comment.dto.SaveCommentDTO;
import org.cosmic.cafe.controller.annotation.Login;
import org.cosmic.cafe.domain.Comment.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<UUID> saveComment(@RequestBody SaveCommentDTO commentDTO, @Login UUID memberId) {
        UUID commentId = commentService.saveComment(memberId, commentDTO.getContent(), commentDTO.getPostId(), commentDTO.getParentId());
        return ResponseEntity.ok(commentId);
    }

    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId")String commentId, @Login UUID memberId){
        commentService.deleteComment(commentId,memberId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{commentId}")
    public ResponseEntity<CommentResponseDTO> modifyComment(@RequestBody ModifyCommentDTO modifyCommentDTO, @PathVariable("commentId")String commentId, @Login UUID memberId){
        CommentResponseDTO comment = commentService.modifyComment(modifyCommentDTO.getContent(),commentId,memberId);
        return ResponseEntity.ok(comment);
    }
}
