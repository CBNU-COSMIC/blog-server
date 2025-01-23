package org.cosmic.cafe.domain;

import java.util.List;

public interface PostRepository {
    public Post save(Post post);
    public Post findByTitle(String title);
    public List<Post> findAll();
    public void deleteById(Long id);
    public Post findById(Long id);
}
