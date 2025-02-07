package org.cosmic.cafe.domain.post;

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
}
