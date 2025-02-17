package org.cosmic.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.application.post.PostService;
import org.cosmic.cafe.application.post.dto.PostDetailResponse;
import org.cosmic.cafe.controller.annotation.Login;
import org.cosmic.cafe.dto.PostUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable UUID postId) {
        PostDetailResponse response = postService.getPostDetail(postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable UUID postId, @Login UUID memberId, @RequestBody PostUpdateRequest postUpdateRequest) {
        postService.update(postUpdateRequest.title(), postUpdateRequest.content(), postId, memberId);
        return ResponseEntity.ok().build();
    }
}
