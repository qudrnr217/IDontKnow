package com.idk.api.user.controller;

import com.idk.api.MvcTest;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.dto.UserRequest;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.security.token.Token;
import com.idk.api.user.service.UserService;
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
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("User API 문서화")
@WebMvcTest(UserController.class)
public class UserControllerTest extends MvcTest {

    @MockBean
    private UserService userService;

    private User user;
    private DistrictCode districtCode;
    private Token accessToken;


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
        accessToken = Token.builder()
                .token("access-token")
                .expiredAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("회원가입")
    public void join() throws Exception{
        UserRequest.Join request = UserRequest.Join.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .districtId(user.getDistrictCode().getId())
                .gender(user.getGender())
                .age(user.getAge())
                .build();

        UserResponse.OnlyId response = UserResponse.OnlyId.build(user);
        given(userService.join(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.post("/api/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("join",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("유저 비밀번호"),
                                fieldWithPath("districtId").type(JsonFieldType.NUMBER).description("지역구 ID"),
                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        ),
                        responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID")
                        )
                ));
        verify(userService).join(any());

    }

    @Test
    @DisplayName("로그인")
    public void login() throws Exception{
        UserRequest.Login request = UserRequest.Login.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        UserResponse.Login response = UserResponse.Login.build(user, accessToken);

        given(userService.login(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("login",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("유저 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                fieldWithPath("districtId").type(JsonFieldType.NUMBER).description("지역구"),
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("Access-Token"),
                                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("Refresh-Token").optional()
                        )
                ));
        verify(userService).login(any());
    }

    @Test
    @DisplayName("이메일_중복_확인")
    public void checkEmailDuplicate() throws Exception{
        boolean response = false;

        given(userService.checkEmailDuplication(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/users/email/{email}/exist", user.getEmail()));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("email-duplication",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("email").description("이메일")

                        )
                ));
        verify(userService).checkEmailDuplication(any());

    }

    @Test
    @DisplayName("이름_중복_확인")
    public void checkNameDuplicate() throws Exception{
        boolean response = false;

        given(userService.checkNameDuplication(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/users/name/{name}/exist", user.getName()));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("name-duplication",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("name").description("이름")

                        )
                ));
        verify(userService).checkNameDuplication(any());

    }

    @Test
    @DisplayName("비밀번호_재설정")
    public void resetPassword() throws Exception{
        UserRequest.RePassword request = UserRequest.RePassword.builder()
                .email(user.getEmail())
                .build();

        boolean response = true;

        given(userService.resetPassword(any())).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.patch("/api/users/pw")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("reset-password",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일")
                        )
                ));
        verify(userService).resetPassword(any());

    }


}
