package org.cosmic.cafe.domain.Comment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Comment save(Comment comment);
    Optional<Comment> findById(UUID id);
    List<Comment> findByPostId(UUID postId);
    List<Comment> findByParentId(UUID parentId);
    void delete(UUID id);
    void deleteByPostId(UUID postId);
    void deleteByParentId(UUID parentId);
}
