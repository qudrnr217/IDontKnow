package com.idk.api.user.controller;

import com.idk.api.MvcTest;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.Role;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("MyPage API 문서화")
@WebMvcTest(MyPageController.class)
public class MyPageControllerTest extends MvcTest {

    @MockBean
    private MyPageService myPageService;
    private User user;
    private DistrictCode districtCode;
    private Vote vote;

    @BeforeEach
    public void setup(){
        districtCode = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();

        user = User.builder()
                .id(1L)
                .role(Role.USER)
                .email("chickenlover@idontknow.com")
                .name("chickenlover")
                .password("1234")
                .gender("F")
                .districtCode(districtCode)
                .age(20)
                .build();


//        vote = Vote.builder()
//                .id(1L)
//                .user(user)
//                .title("무슨 치킨 먹을까요?")
//                .optionA("교촌치킨")
//                .optionB("노랑통닭")
//                .status(false)
//                .category(Category.MENU)
//                .subCategory(SubCategory.CHICKEN)
//                .aCount(0)
//                .bCount(0)
//                .hitCount(1)
//                .commentCount(1)
//                .adminDeleted(false)
//                .result(null)
//                .ballotList()
    }

    @Test
    @DisplayName("유저정보_조회")
    public void getUserInfo() throws Exception{
        MyPageResponse.UserInfo response = MyPageResponse.UserInfo.build(user);

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

        MyPageResponse.UserInfo response = MyPageResponse.UserInfo.build(user);
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

        UserResponse.OnlyId response = UserResponse.OnlyId.build(user);

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
                .curPassword(user.getPassword())
                .newPassword("4321")
                .build();

        UserResponse.OnlyId response = UserResponse.OnlyId.build(user);

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

//    @Test
//    @DisplayName("작성한_투표_목록_조회")
//    public void getVoteList() throws Exception{
//        Page<VoteResponse.GetOne> response = Page<VoteResponse.GetOne.build()>;
//
//        given(myPageService.getUserInfo(any())).willReturn(response);
//        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/mypage/users/{userId}/info", 1L));
//
//        results.andExpect(status().isOk())
//                .andDo(print())
//                .andDo(document("get-user-info",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                parameterWithName("userId").description("유저 ID")
//
//                        ),
//                        responseFields(
//                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("유저 ID"),
//                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
//                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
//                                fieldWithPath("districtId").type(JsonFieldType.NUMBER).description("지역구 ID"),
//                                fieldWithPath("districtName").type(JsonFieldType.STRING).description("지역구 이름"),
//                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
//                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
//                        )
//                ));
//        verify(myPageService).getUserInfo(any());
//
//    }

}
