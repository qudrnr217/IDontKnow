package com.idk.api.user.controller;

import com.idk.api.MvcTest;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.dto.MyPageRequest;
import com.idk.api.user.dto.MyPageResponse;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.service.MyPageService;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.dto.VoteResponse;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("MyPage API 문서화")
@WebMvcTest(MyPageController.class)
public class MyPageControllerTest extends MvcTest {

    @MockBean
    private MyPageService myPageService;
    private User user1, user2;
    private DistrictCode districtCode;
    private Vote vote1, vote2;
    private final List<Vote> voteList = new ArrayList<>();

    @BeforeEach
    public void setup(){
        districtCode = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();

        user1 = User.builder()
                .id(1L)
                .name("치킨러버")
                .email("chickenLover@idontknow.com")
                .districtCode(districtCode)
                .gender("M")
                .age(20).build();
        user2 = User.builder()
                .id(1L)
                .name("치킨마니아")
                .email("chickenMania@idontknow.com")
                .districtCode(districtCode)
                .gender("F")
                .age(20)
                .build();

        vote1 = Vote.builder()
                .id(1L)
                .user(user1)
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

        voteList.add(vote1);
        voteList.add(vote2);
    }

    @Test
    @DisplayName("유저정보_조회")
    public void getUserInfo() throws Exception{
        MyPageResponse.UserInfo response = MyPageResponse.UserInfo.build(user1);

        given(myPageService.getUserInfo(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/info", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-user-info",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                fieldWithPath("districtId").type(JsonFieldType.NUMBER).description("지역구 ID"),
                                fieldWithPath("districtName").type(JsonFieldType.STRING).description("지역구 이름"),
                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        )
                ));
        verify(myPageService).getUserInfo(any());

    }

    @Test
    @DisplayName("유저정보_수정")
    public void updateUserInfo() throws Exception{

        MyPageResponse.UserInfo response = MyPageResponse.UserInfo.build(user1);
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("districtId", "0");
        requestParam.set("gender", "M");
        requestParam.set("age", "20");

        given(myPageService.updateUserInfo(anyLong(), any(), anyInt(), any(), anyInt())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.patch("/api/mypage/users/{userId}/info", 1L)
                .params(requestParam)
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("update-user-info",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                fieldWithPath("districtId").type(JsonFieldType.NUMBER).description("지역구 ID"),
                                fieldWithPath("districtName").type(JsonFieldType.STRING).description("지역구 이름"),
                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        )
                ));
        verify(myPageService).updateUserInfo(anyLong(), any(), anyInt(), any(), anyInt());

    }

    @Test
    @DisplayName("유저정보_삭제")
    public void deleteUserInfo() throws Exception{

        UserResponse.OnlyId response = UserResponse.OnlyId.build(user1);

        given(myPageService.deleteUserInfo(any(), any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.patch("/api/mypage/users/{userId}", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("delete-user-info",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID")
                        )
                ));
        verify(myPageService).deleteUserInfo(any(), any());

    }

    @Test
    @DisplayName("비밀번호_변경")
    public void changePassword() throws Exception{
        MyPageRequest.UserPassword request = MyPageRequest.UserPassword.builder()
                .curPassword("1234")
                .newPassword("4321")
                .build();

        UserResponse.OnlyId response = UserResponse.OnlyId.build(user1);

        given(myPageService.updateUserPassword(any(), any(), any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.patch("/api/mypage/users/{userId}/pw", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("update-password",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        requestFields(
                                fieldWithPath("curPassword").type(JsonFieldType.STRING).description("현재 비밀번호"),
                                fieldWithPath("newPassword").type(JsonFieldType.STRING).description("새로운 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID")
                        )
                ));
        verify(myPageService).updateUserPassword(any(), any(), any());

    }

    @Test
    @DisplayName("작성한_투표_목록_조회")
    public void getVoteList() throws Exception {
        Page<Vote> votePage = new PageImpl<>(voteList, PageRequest.of(0, 2), voteList.size());
        Page<VoteResponse.GetOne> response = votePage.map(VoteResponse.GetOne::build);
        given(myPageService.getVoteList(anyLong(), anyLong(), any(), anyBoolean())).willReturn(response);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/votes", 1L)
                .queryParam("status", "false")
                .queryParam("lastVoteId", "0")
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user_votes_list",
                        requestParameters(
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
        verify(myPageService).getVoteList(anyLong(), anyLong(), any(), anyBoolean());
    }


    @Test
    @DisplayName("참여한_투표_목록_조회")
    public void getBallotList() throws Exception {
        Page<Vote> votePage = new PageImpl<>(voteList, PageRequest.of(0, 2), voteList.size());
        Page<VoteResponse.GetOne> response = votePage.map(VoteResponse.GetOne::build);
        given(myPageService.getBallotList(anyLong(), anyLong(), any(), anyBoolean())).willReturn(response);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/ballots", 1L)
                .queryParam("status", "false")
                .queryParam("lastVoteId", "0")
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user_ballots_list",
                        requestParameters(
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
        verify(myPageService).getBallotList(anyLong(), anyLong(), any(), anyBoolean());
    }

    @Test
    @DisplayName("예측률_조회")
    public void getRate() throws Exception{
        MyPageResponse.Rate response = MyPageResponse.Rate.builder()
                .id(user1.getId())
                .ballotCount(2)
                .correctCount(1)
                .build();

        given(myPageService.getRate(anyLong(), any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/rate", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-user-rate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("ballotCount").type(JsonFieldType.NUMBER).description("참여한 투표 개수"),
                                fieldWithPath("correctCount").type(JsonFieldType.NUMBER).description("맞춘 투표 개수")
                        )
                ));
        verify(myPageService).getRate(anyLong(), any());

    }

    @Test
    @DisplayName("로그아웃")
    public void logout() throws Exception{
        UserResponse.OnlyId response = UserResponse.OnlyId.build(user1);

        given(myPageService.logout(anyLong(), any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/logout", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("logout",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("userId").description("유저 ID")

                        ),
                        responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID")
                        )
                ));
        verify(myPageService).logout(anyLong(), any());

    }


}
