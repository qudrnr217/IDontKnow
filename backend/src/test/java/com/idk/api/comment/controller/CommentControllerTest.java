package com.idk.api.comment.controller;

import com.idk.api.MvcTest;
import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.comment.dto.CommentRequest;
import com.idk.api.comment.dto.CommentResponse;
import com.idk.api.comment.service.CommentService;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Comment API 문서화")
@WebMvcTest(CommentController.class)
public class CommentControllerTest extends MvcTest {

    @MockBean
    private CommentService commentService;
    private Vote vote;
    private User user1, user2;
    private Comment comment;

    @BeforeEach
    public void setUp() {
        DistrictCode districtCode = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();

        user1 = User.builder()
                .id(7L)
                .name("치킨")
                .email("chicken@idontknow.com")
                .districtCode(districtCode)
                .gender("F")
                .age(20).build();
        user2 = User.builder()
                .id(1L)
                .name("치킨러버")
                .email("chickenLover@idontknow.com")
                .districtCode(districtCode)
                .gender("M")
                .age(20).build();

        vote = Vote.builder()
                .id(1L)
                .user(user1)
                .title("무슨 치킨 먹을까?")
                .optionA("교촌치킨")
                .optionB("노랑통닭")
                .category(Category.MENU)
                .subCategory(SubCategory.CHICKEN)
                .hitCount(10)
                .commentCount(3)
                .aCount(4)
                .bCount(2)
                .status(false)
                .build();
        vote.setCreatedAt(LocalDateTime.now().minusDays(3L));

        comment = Comment.builder()
                .id(1L)
                .vote(vote)
                .user(user2)
                .content("당연히 교촌")
                .build();
        comment.setCreatedAt(LocalDateTime.now().minusMinutes(30L));
    }

    @Test
    @DisplayName("댓글 생성")
    public void create() throws Exception {
        CommentRequest.Create request = CommentRequest.Create.builder()
                .voteId(1L)
                .content("당연히 교촌")
                .build();
        String content = objectMapper.writeValueAsString(request);
        CommentResponse.OnlyId response = CommentResponse.OnlyId.build(comment);
        given(commentService.create(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("comments_create",
                        requestFields(
                                fieldWithPath("voteId").type(JsonFieldType.NUMBER).description("투표 식별자"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("댓글 식별자")
                        )
                ));

    }

    @Test
    @DisplayName("댓글 수정")
    public void update() throws Exception {
        CommentRequest.Update request = CommentRequest.Update.builder()
                .content("교촌 좋아요")
                .build();
        String content = objectMapper.writeValueAsString(request);
        CommentResponse.OnlyId response = CommentResponse.OnlyId.build(comment);
        given(commentService.update(any(), any(), any())).willReturn(response);

        ResultActions results = mvc.perform(patch("/api/comments/{commentId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("comments_update",
                        pathParameters(
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        requestFields(
                                fieldWithPath("content").type(JsonFieldType.STRING).description("새 댓글 내용")

                        ),
                        responseFields(
                                fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 삭제")
    public void delete() throws Exception {
        CommentResponse.OnlyId response = CommentResponse.OnlyId.build(comment);
        given(commentService.delete(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.delete("/api/comments/{commentId}", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("comments_delete",
                        pathParameters(
                                parameterWithName("commentId").description("댓글 식별자")
                        ),
                        responseFields(
                                fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("댓글 상세 조회")
    public void getComment() throws Exception {
        CommentResponse.GetOne response = CommentResponse.GetOne.build(1L, comment);
        given(commentService.getOne(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/comments/{commentId}", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("comments_detail",
                        pathParameters(
                                parameterWithName("commentId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("작성자 식별자"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("작성자 이름"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("작성일시"),
                                fieldWithPath("checkAuthor").type(JsonFieldType.BOOLEAN).description("투표 작성자와 댓글 작성자의 일치여부")
                        )
                ));
    }

}
