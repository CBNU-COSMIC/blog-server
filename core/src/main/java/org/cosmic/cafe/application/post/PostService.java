package org.cosmic.cafe.application.post;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.cosmic.cafe.domain.post.exception.PostErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void update(String title, String content, UUID memberId, UUID postId) {
        Post post = getPostById(postId);

    }

    private Post getPostById(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new BadRequestException("게시글이 존재하지 않습니다.", PostErrorCode.NOT_FOUND));
    }

}
