package org.cosmic.cafe.application.comment;

import lombok.RequiredArgsConstructor;
import org.cosmic.cafe.domain.Comment.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    
}
