package org.cosmic.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.post.PostService;
import org.cosmic.cafe.application.post.dto.PostCreationPayload;
import org.cosmic.cafe.application.post.dto.PostDetailResponse;
import org.cosmic.cafe.application.post.dto.PostListResponse;
import org.cosmic.cafe.controller.annotation.Login;
import org.cosmic.cafe.dto.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@Login UUID memberId, @RequestBody PostCreationPayload postCreationPayload) {
        postService.createPost(memberId, postCreationPayload);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable UUID postId) {
        PostDetailResponse response = postService.getPostDetail(postId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PostListResponse>> getPosts(
            @RequestParam String boardId,
            @RequestParam(defaultValue = "1") int page) {
        List<PostListResponse> response = postService.getPostsByBoardId(boardId, page);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable UUID postId, @Login UUID memberId, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.update(postUpdateRequest.title(), postUpdateRequest.content(), postId, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable UUID postId, @Login UUID memberId) {
        postService.deletePost(postId, memberId);
        return ResponseEntity.ok().build();
    }
}
