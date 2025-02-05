package org.cosmic.cafe.infra.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.Comment.Comment;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentCoreRepository implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    @Override
    public Comment findById(UUID id) {
        return null;
    }

    @Override
    public List<Comment> findByPostId(UUID postId) {
        return List.of();
    }

    @Override
    public List<Comment> findByParentId(UUID parentId) {
        return List.of();
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void deleteByPostId(UUID postId) {

    }

    @Override
    public void deleteByParentId(UUID parentId) {

    }
}
