package org.cosmic.cafe.infra.post;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.cosmic.cafe.infra.post.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostCoreRepository implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post){
        return null;
    }

    @Override
    public Optional<Post> findById(UUID id){
        return Optional.empty();
    }

    @Override
    public List<Post> findByTitle(String title){
        return List.of();
    }

    @Override
    public List<Post> findAll(){
        return List.of();
    }

    @Override
    public void deleteById(UUID id){

    }

}
