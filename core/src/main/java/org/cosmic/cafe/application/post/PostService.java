package org.cosmic.cafe.application.post;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.post.dto.PostDetailResponse;
import org.cosmic.cafe.application.post.dto.PostListResponse;
import org.cosmic.cafe.domain.post.Post;
import org.cosmic.cafe.domain.post.PostRepository;
import org.cosmic.cafe.domain.post.exception.PostErrorCode;
import org.cosmic.cafe.exception.type.BadRequestException;
import org.cosmic.cafe.exception.type.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post getPost(UUID postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("해당 게시글이 존재하지 않습니다.", PostErrorCode.NOT_FOUND));
    }

    public PostDetailResponse getPostDetail(UUID postId) {
        Post post = this.getPost(postId);
        
        // TODO: Member 구현 후 실제 작성자 정보 조회 로직으로 대체 필요
        String author = "Unknown"; // 임시로 Unknown 처리
        
        return PostDetailResponse.of(post, author);
    }

    public List<PostListResponse> getPostsByBoardId(String boardId, int page) {
        Pageable pageable = PageRequest.of(page-1, 10);
        Page<Post> posts = postRepository.findByBoardIdOrderByCreatedAtDesc(boardId, pageable);

        return posts.getContent().stream()
                .map(post -> {
                    // TODO: Member 구현 후 실제 작성자 정보 조회 로직으로 대체 필요
                    String author = "Unknown"; // 임시로 Unknown 처리
                    return PostListResponse.of(post, author);
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(String title, String content, UUID memberId, UUID postId) {
        Post post = this.getPost(postId);

        if (post.isNotWritten(memberId)) {
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

}
