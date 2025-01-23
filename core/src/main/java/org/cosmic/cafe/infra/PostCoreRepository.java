package org.bee.metro.core.post.infra;

import lombok.RequiredArgsConstructor;
import org.bee.metro.core.post.domain.Post;
import org.bee.metro.core.post.domain.PostRepository;
import org.bee.metro.core.post.entity.PostEntity;
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
        PostEntity postEntity = PostEntity.from(post);
        PostEntity saveedPostEntity = postJpaRepository.save(postEntity);
        return Post.fromEntity(saveedPostEntity);
    }

    @Override
    public Post findById(Long id){
        Optional<PostEntity> postEntity = postJpaRepository.findById(id);
        return postEntity.map(Post::fromEntity).orElse(null);
    }

    @Override
    public Post findByTitle(String title){
        PostEntity postEntity = postJpaRepository.findByTitle(title);
        if(postEntity == null){
            return null;
        }
        return Post.fromEntity(postEntity);
    }

    @Override
    public List<Post> findAll(){
        List<PostEntity> postEntities = postJpaRepository.findAll();
        if(postEntities.isEmpty()){
            return null;
        }
        return postEntities.stream().map(Post::fromEntity).toList();
    }

    @Override
    public void deleteById(Long id){
        postJpaRepository.deleteById(id);
    }

}
