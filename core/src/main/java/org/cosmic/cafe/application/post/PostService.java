package org.cosmic.cafe.application.post;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.cosmic.cafe.domain.post.exception.PostErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void update(String title, String content, UUID memberId, UUID postId) {
        Post post = getPostById(postId);

        if (!(post.getMemberId() == memberId)) {
            throw new BadRequestException("해당 게시글 수정 권한이 없습니다. 게시글 아이디 : %s".formatted(postId), PostErrorCode.UPDATE_PERMISSION_DENIED);
        }

        Post updatedPost = Post.builder()
                .id(post.getId())
                .memberId(post.getMemberId())
                .boardId(post.getBoardId())
                .title(title)
                .content(content)
                .hits(post.getHits())
                .build();

        postRepository.save(updatedPost);
    }

    private Post getPostById(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("게시글이 존재하지 않습니다.", PostErrorCode.NOT_FOUND));
    }

}
