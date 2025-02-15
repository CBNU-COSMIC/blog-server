package org.cosmic.cafe.application.post;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.post.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


}
