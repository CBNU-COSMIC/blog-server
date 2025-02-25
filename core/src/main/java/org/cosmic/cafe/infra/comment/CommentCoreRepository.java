package org.cosmic.cafe.infra.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.cosmic.cafe.infra.comment.entity.CommentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentCoreRepository implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity commentEntity = CommentEntity.of(comment);
        CommentEntity savedCommentEntity = commentJpaRepository.save(commentEntity);
        return Comment.of(savedCommentEntity);
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        Optional<CommentEntity> commentEntity = commentJpaRepository.findById(id);
        return commentEntity.map(Comment::of);
    }

    @Override
    public List<Comment> findByPostId(UUID postId) {
        // TODO: 시간별로 정렬
        // TODO: 정렬이 제대로 되었는지 테스트
        List<CommentEntity> commentEntities = commentJpaRepository.findByPostId(postId);

        return commentEntities.stream()
                .map(Comment::of)
                .toList();
    }

    @Override
    public List<Comment> findByParentId(UUID parentId) {
        List<CommentEntity> commentEntities = commentJpaRepository.findByParentId(parentId);

        return commentEntities.stream()
                .map(Comment::of)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        commentJpaRepository.deleteById(id);
    }

    @Override
    public void deleteByPostId(UUID postId) {
        commentJpaRepository.deleteByPostId(postId);
    }

    @Override
    public void deleteByParentId(UUID parentId) {
        commentJpaRepository.deleteByParentId(parentId);
    }
}
