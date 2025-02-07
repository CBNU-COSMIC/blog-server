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
        PostEntity postEntity = PostEntity.from(post);
        PostEntity saveedPostEntity = postJpaRepository.save(postEntity);
        return Post.of(saveedPostEntity);    }

    @Override
    public Optional<Post> findById(UUID id){
        Optional<PostEntity> postEntity = postJpaRepository.findById(id);
        return postEntity.map(Post::of);
    }

    @Override
    public List<Post> findByTitle(String title){
        List<PostEntity> postEntities = postJpaRepository.findByTitle(title);
        return postEntities.stream().map(Post::of).toList();
    }

    @Override
    public List<Post> findAll(){
        List<PostEntity> postEntities = postJpaRepository.findAll();
        return postEntities.stream().map(Post::of).toList();
    }

    @Override
    public void deleteById(UUID id){
        postJpaRepository.deleteById(id);
    }

    @Override
    public List<Post> findByTitleContaining(String title){
        List<PostEntity> postEntities = postJpaRepository.findByTitleContaining(title);
        return postEntities.stream().map(Post::of).toList();
    }

    @Override
    public List<Post> findByBoardId(String boardId){
        List<PostEntity> postEntities = postJpaRepository.findByBoardId(boardId);
        return postEntities.stream().map(Post::of).toList();
    }

}
