package org.cosmic.cafe.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository {
    public Post save(Post post);
    public Optional<Post> findById(UUID id);
    public List<Post> findByTitle(String title);
    public List<Post> findAll();
    public void deleteById(UUID id);
    public List<Post> findByTitleContaining(String title);
    public List<Post> findByBoardId(String boardId);
    Page<Post> findByBoardIdOrderByCreatedAtDesc(String boardId, Pageable pageable);
}
