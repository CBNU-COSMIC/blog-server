package org.cosmic.cafe.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cosmic.cafe.CommentControllerTest;
import org.cosmic.cafe.application.AuthService;
import org.cosmic.cafe.application.comment.CommentService;
import org.cosmic.cafe.controller.AuthController;
import org.cosmic.cafe.controller.CommentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest({AuthController.class, CommentController.class})
@AutoConfigureMockMvc
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockitoBean
    protected AuthService authService;

    @MockitoBean
    protected AuthenticationContext authenticationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockitoBean
    protected CommentService commentService;
}
