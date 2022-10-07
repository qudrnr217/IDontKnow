package com.idk.api.vote.controller;

import com.idk.api.MvcTest;
import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.service.VoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Vote API 문서화")
@WebMvcTest(VoteController.class)
public class VoteControllerTest extends MvcTest {

    @MockBean
    private VoteService voteService;
    private Vote vote1;
    private Vote vote2;
    private Ballot ballot1;
    private final List<Comment> commentList = new ArrayList<>();
    private final List<Vote> voteList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        DistrictCode districtCode = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();

        User user1 = User.builder()
                .id(1L)
                .name("치킨러버")
                .email("chickenLover@idontknow.com")
                .districtCode(districtCode)
                .gender("M")
                .age(20).build();
        User user2 = User.builder()
                .id(2L)
                .name("치킨마니아")
                .email("chickenMania@idontknow.com")
                .districtCode(districtCode)
                .gender("F")
                .age(20)
                .build();
        User user3 = User.builder()
                .id(3L)
                .name("치킨짱")
                .email("chickenZzang@idontknow.com")
                .districtCode(districtCode)
                .gender("M")
                .age(10)
                .build();

        vote1 = Vote.builder()
                .id(1L)
                .user(user3)
                .title("무슨 치킨 먹을까?")
                .optionA("교촌치킨")
                .optionB("노랑통닭")
                .category(Category.MENU)
                .subCategory(SubCategory.CHICKEN)
                .hitCount(3)
                .commentCount(3)
                .aCount(1)
                .bCount(1)
                .status(false)
                .build();
        vote1.setCreatedAt(LocalDateTime.now().minusDays(3L));

        vote2 = Vote.builder()
                .id(2L)
                .user(user2)
                .title("점심 메뉴로 어떤 치킨 먹을까?")
                .optionA("BBQ")
                .optionB("KFC")
                .category(Category.MENU)
                .subCategory(SubCategory.CHICKEN)
                .hitCount(2)
                .commentCount(0)
                .aCount(0)
                .bCount(0)
                .status(true)
                .build();
        vote2.setCreatedAt(LocalDateTime.now().minusDays(2L));
        vote2.setDeletedAt(LocalDateTime.now());

        Vote vote3 = Vote.builder()
                .id(3L)
                .user(user1)
                .title("저녁 메뉴로 어떤 치킨 먹을까?")
                .optionA("교촌치킨")
                .optionB("KFC")
                .category(Category.MENU)
                .subCategory(SubCategory.CHICKEN)
                .hitCount(1)
                .commentCount(0)
                .aCount(0)
                .bCount(0)
                .status(false)
                .build();
        vote3.setCreatedAt(LocalDateTime.now().minusDays(1L));

        voteList.add(vote1);
        voteList.add(vote3);

        ballot1 = Ballot.builder()
                .id(1L)
                .vote(vote1)
                .user(user1)
                .choice("A")
                .build();
        ballot1.setCreatedAt(LocalDateTime.now().minusHours(1L));

        Ballot ballot2 = Ballot.builder()
                .id(2L)
                .vote(vote1)
                .user(user2)
                .choice("B")
                .build();
        ballot2.setCreatedAt(LocalDateTime.now().minusHours(1L));

        Comment comment1 = Comment.builder()
                .id(1L)
                .vote(vote1)
                .user(user1)
                .content("당연히 교촌")
                .build();
        comment1.setCreatedAt(LocalDateTime.now().minusMinutes(30L));

        Comment comment2 = Comment.builder()
                .id(2L)
                .vote(vote1)
                .user(user2)
                .content("당연히 노통")
                .build();
        comment2.setCreatedAt(LocalDateTime.now().minusMinutes(20L));

        Comment comment3 = Comment.builder()
                .id(3L)
                .vote(vote1)
                .user(user3)
                .content("둘다 좋아요")
                .build();
        comment3.setCreatedAt(LocalDateTime.now().minusMinutes(10L));

        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
    }

    @Test
    @DisplayName("투표 생성")
    public void create() throws Exception {
        VoteRequest.Create request = VoteRequest.Create.builder()
                .title("무슨 치킨 먹을까?")
                .category("메뉴")
                .subCategory("치킨")
                .optionA("교촌치킨")
                .optionB("노랑통닭")
                .build();
        String content = objectMapper.writeValueAsString(request);
        VoteResponse.OnlyId response = VoteResponse.OnlyId.build(vote2);
        given(voteService.create(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(post("/api/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_create",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("투표 제목"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("투표 카테고리"),
                                fieldWithPath("subCategory").type(JsonFieldType.STRING).description("투표 세부카테고리"),
                                fieldWithPath("optionA").type(JsonFieldType.STRING).description("투표 선택지 A"),
                                fieldWithPath("optionB").type(JsonFieldType.STRING).description("투표 선택지 B")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("투표 식별자")
                        )
                ));

    }

    @Test
    @DisplayName("투표 상세 조회")
    public void getOne() throws Exception {
        VoteResponse.Info response = VoteResponse.Info.build(vote1, ballot1, commentList, vote1.getACount(), vote1.getBCount());
        given(voteService.getOne(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/votes/{voteId}", 1L).characterEncoding("UTF-8"));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_detail",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("voteId").type(JsonFieldType.NUMBER).description("투표 식별자"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("투표 카테고리"),
                                fieldWithPath("subCategory").type(JsonFieldType.STRING).description("투표 세부카테고리"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("투표 제목"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("투표 작성자 식별자"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("투표 작성자 이름"),
                                fieldWithPath("hitCount").type(JsonFieldType.NUMBER).description("투표 조회수"),
                                fieldWithPath("commentCount").type(JsonFieldType.NUMBER).description("투표 댓글수"),
                                fieldWithPath("optionA").type(JsonFieldType.STRING).description("투표 선택지 A"),
                                fieldWithPath("acount").type(JsonFieldType.NUMBER).description("A 선택지 투표 참여수"),
                                fieldWithPath("optionB").type(JsonFieldType.STRING).description("투표 선택지 B"),
                                fieldWithPath("bcount").type(JsonFieldType.NUMBER).description("B 선택지 투표 참여수"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("투표 작성일자"),
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("투표 상태 (false : 진행, true : 종료)"),
                                fieldWithPath("result").type(JsonFieldType.NULL).description("투표 종료 결과 (진행 : null, 종료 : E/A/B)"),
                                fieldWithPath("ballotId").type(JsonFieldType.NUMBER).description("투표 참여 식별자 (현재 로그인한 회원이 투표를 참여한 경우 : 식별자 반환, 안한 경우 : null)"),
                                fieldWithPath("voted").type(JsonFieldType.STRING).description("투표 참여 선택값 (A/B)"),
                                fieldWithPath("commentList[].commentId").type(JsonFieldType.NUMBER).description("댓글 식별자"),
                                fieldWithPath("commentList[].userId").type(JsonFieldType.NUMBER).description("댓글 작성자 식별자"),
                                fieldWithPath("commentList[].name").type(JsonFieldType.STRING).description("댓글 작성자 이름"),
                                fieldWithPath("commentList[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                fieldWithPath("commentList[].createdAt").type(JsonFieldType.STRING).description("댓글 작성일자"),
                                fieldWithPath("commentList[].checkAuthor").type(JsonFieldType.BOOLEAN).description("투표와 댓글의 작성자 동일여부 (같은 경우 : true, 아닌 경우 : false)")
                        )
                ));
    }

    @Test
    @DisplayName("투표 상태 변경")
    public void update() throws Exception {
        VoteRequest.ChangeStatus request = VoteRequest.ChangeStatus.builder()
                .status(true)
                .build();
        String content = objectMapper.writeValueAsString(request);
        VoteResponse.OnlyId response = VoteResponse.OnlyId.build(vote2);
        given(voteService.changeStatus(any(), any(), any())).willReturn(response);

        ResultActions results = mvc.perform(patch("/api/votes/{voteId}", 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_update",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        requestFields(
                                fieldWithPath("status").type(JsonFieldType.BOOLEAN).description("바꾸고자 하는 투표 상태(언제나 true)")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("투표 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("투표 삭제")
    public void delete() throws Exception {
        VoteResponse.OnlyId response = VoteResponse.OnlyId.build(vote2);
        given(voteService.delete(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.delete("/api/votes/{voteId}", 2L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_delete",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("투표 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("카테고리별 투표 목록 조회")
    public void getList() throws Exception {
        Page<Vote> votePage = new PageImpl<>(voteList, PageRequest.of(0, 2), voteList.size());
        Page<VoteResponse.GetOne> response = votePage.map(VoteResponse.GetOne::build);
        given(voteService.getList(anyLong(), anyString(), anyBoolean())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/votes")
                .queryParam("category", "메뉴")
                .queryParam("status", "false")
                .queryParam("lastVoteId", "0")
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_list",
                        requestParameters(
                                parameterWithName("category").description("카테고리 : 메뉴/스타일/장소"),
                                parameterWithName("status").description("투표 상태 : false(진행)/true(종료)"),
                                parameterWithName("lastVoteId").description("마지막 투표 식별자(처음 조회 시 0)")
                        ),
                        responseFields(
                                fieldWithPath("content[].voteId").type(JsonFieldType.NUMBER).description("투표 식별자"),
                                fieldWithPath("content[].category").type(JsonFieldType.STRING).description("투표 카테고리"),
                                fieldWithPath("content[].subCategory").type(JsonFieldType.STRING).description("투표 세부카테고리"),
                                fieldWithPath("content[].title").type(JsonFieldType.STRING).description("투표 제목"),
                                fieldWithPath("content[].userId").type(JsonFieldType.NUMBER).description("투표 작성자 식별자"),
                                fieldWithPath("content[].name").type(JsonFieldType.STRING).description("투표 작성자 이름"),
                                fieldWithPath("content[].hitCount").type(JsonFieldType.NUMBER).description("투표 조회수"),
                                fieldWithPath("content[].commentCount").type(JsonFieldType.NUMBER).description("투표 댓글수"),
                                fieldWithPath("content[].optionA").type(JsonFieldType.STRING).description("투표 선택지 A"),
                                fieldWithPath("content[].optionB").type(JsonFieldType.STRING).description("투표 선택지 B"),
                                fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("투표 작성일자"),
                                fieldWithPath("content[].status").type(JsonFieldType.BOOLEAN).description("투표 상태 (false : 진행, true : 종료)"),
                                fieldWithPath("totalPages").description("전체 페이지 수"),
                                fieldWithPath("totalElements").description("전체 투표 개수"),
                                fieldWithPath("last").description("마지막 페이지인지 식별하는 값"),
                                fieldWithPath("pageable.sort.unsorted").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.sort.sorted").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.sort.empty").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.offset").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.pageNumber").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.pageSize").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.paged").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("pageable.unpaged").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("number").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("sort.unsorted").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("sort.sorted").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("sort.empty").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("size").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("first").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("numberOfElements").description("pageable 자동 값 반환 - 참고 x"),
                                fieldWithPath("empty").description("pageable 자동 값 반환 - 참고 x")

                        )
                ));
    }

    @Test
    @DisplayName("카테고리별 조회수 Top3")
    public void getTop3() throws Exception {
        List<VoteResponse.GetOne> response = voteList.stream().map(VoteResponse.GetOne::build).collect(Collectors.toList());
        given(voteService.getTop3(any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/votes/top3")
                .param("category", "메뉴")
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("votes_top3",
                        requestParameters(
                                parameterWithName("category").description("카테고리 : 메뉴/스타일/장소")
                        ),
                        responseFields(
                                fieldWithPath("[].voteId").type(JsonFieldType.NUMBER).description("투표 식별자"),
                                fieldWithPath("[].category").type(JsonFieldType.STRING).description("투표 카테고리"),
                                fieldWithPath("[].subCategory").type(JsonFieldType.STRING).description("투표 세부카테고리"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("투표 제목"),
                                fieldWithPath("[].userId").type(JsonFieldType.NUMBER).description("투표 작성자 식별자"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("투표 작성자 이름"),
                                fieldWithPath("[].hitCount").type(JsonFieldType.NUMBER).description("투표 조회수"),
                                fieldWithPath("[].commentCount").type(JsonFieldType.NUMBER).description("투표 댓글수"),
                                fieldWithPath("[].optionA").type(JsonFieldType.STRING).description("투표 선택지 A"),
                                fieldWithPath("[].optionB").type(JsonFieldType.STRING).description("투표 선택지 B"),
                                fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("투표 작성일자"),
                                fieldWithPath("[].status").type(JsonFieldType.BOOLEAN).description("투표 상태 (false : 진행, true : 종료)")
                        )
                ));
    }
}
