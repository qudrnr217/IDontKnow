package com.idk.api.ballot.controller;

import com.idk.api.MvcTest;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.controller.BallotController;
import com.idk.api.vote.domain.entity.Ballot;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.dto.*;
import com.idk.api.vote.service.BallotService;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Ballot API 문서화")
@WebMvcTest(BallotController.class)
public class BallotControllerTest extends MvcTest {

    @MockBean
    private BallotService ballotService;
    private Vote vote;
    private User user, user1;
    private DistrictCode districtCode1, districtCode2, districtCode3;
    private Ballot ballot;

    @BeforeEach
    public void setUp() {
        districtCode1 = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();
        districtCode2 = DistrictCode.builder()
                .id(2)
                .name("강동구")
                .build();
        districtCode3 = DistrictCode.builder()
                .id(3)
                .name("강북구")
                .build();

        user = User.builder()
                .id(7L)
                .name("치킨")
                .email("chicken@idontknow.com")
                .districtCode(districtCode1)
                .gender("F")
                .age(20).build();
        user1 = User.builder()
                .id(1L)
                .name("치킨러버")
                .email("chickenLover@idontknow.com")
                .districtCode(districtCode1)
                .gender("M")
                .age(10).build();

        vote = Vote.builder()
                .id(1L)
                .user(user)
                .title("무슨 치킨 먹을까?")
                .optionA("교촌치킨")
                .optionB("노랑통닭")
                .category(Category.MENU)
                .subCategory(SubCategory.CHICKEN)
                .hitCount(10)
                .commentCount(0)
                .aCount(4)
                .bCount(2)
                .status(false)
                .build();
        vote.setCreatedAt(LocalDateTime.now().minusDays(3L));

        ballot = Ballot.builder()
                .id(1L)
                .vote(vote)
                .user(user1)
                .choice("B")
                .build();
        ballot.setCreatedAt(LocalDateTime.now().minusHours(6L));

    }

    @Test
    @DisplayName("투표 참여")
    public void create() throws Exception {
        BallotRequest.Create request = BallotRequest.Create.builder()
                .voteId(1L)
                .choice("B")
                .build();
        String content = objectMapper.writeValueAsString(request);
        BallotResponse.OnlyId response = BallotResponse.OnlyId.build(ballot);
        given(ballotService.create(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(post("/api/ballots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ballots_create",
                        requestFields(
                                fieldWithPath("voteId").type(JsonFieldType.NUMBER).description("투표 식별자"),
                                fieldWithPath("choice").type(JsonFieldType.STRING).description("선택지 (A/B)")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("투표참여 식별자")
                        )
                ));

    }

    @Test
    @DisplayName("투표 취소")
    public void delete() throws Exception {
        BallotResponse.OnlyId response = BallotResponse.OnlyId.build(ballot);
        given(ballotService.delete(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.delete("/api/ballots/{ballotId}", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ballots_delete",
                        pathParameters(
                                parameterWithName("ballotId").description("투표참여 식별자")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("투표참여 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("투표별 지역구에 따른 투표참여 결과 조회")
    public void getBallotsByDistrictCode() throws Exception {
        List<BallotDto.CountByDistrictCode> response = new ArrayList<>();
        response.add(new BallotDto.CountByDistrictCode(districtCode1, "A", 1L));
        response.add(new BallotDto.CountByDistrictCode(districtCode1, "B", 1L));
        response.add(new BallotDto.CountByDistrictCode(districtCode2, "A", 1L));
        response.add(new BallotDto.CountByDistrictCode(districtCode2, "B", 1L));
        response.add(new BallotDto.CountByDistrictCode(districtCode3, "A", 1L));
        response.add(new BallotDto.CountByDistrictCode(districtCode3, "B", 1L));
        given(ballotService.getCountByDistrictCode(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/ballots/votes/{voteId}/district", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ballots_countByDistrict",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("[].districtId").type(JsonFieldType.NUMBER).description("지역구 식별자"),
                                fieldWithPath("[].districtName").type(JsonFieldType.STRING).description("지역구 이름"),
                                fieldWithPath("[].choice").type(JsonFieldType.STRING).description("선택지 (A/B)"),
                                fieldWithPath("[].count").type(JsonFieldType.NUMBER).description("선택지에 대한 투표참여 개수")
                        )
                ));
    }

    @Test
    @DisplayName("투표별 성별에 따른 투표참여 결과 조회")
    public void getBallotsByGender() throws Exception {
        List<BallotDto.CountByGender> response = new ArrayList<>();
        response.add(new BallotDto.CountByGender("F", "A", 2L));
        response.add(new BallotDto.CountByGender("F", "B", 1L));
        response.add(new BallotDto.CountByGender("M", "A", 1L));
        response.add(new BallotDto.CountByGender("M", "B", 2L));
        given(ballotService.getCountByGender(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/ballots/votes/{voteId}/gender", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ballots_countByGender",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("[].gender").type(JsonFieldType.STRING).description("성별 (F/M)"),
                                fieldWithPath("[].choice").type(JsonFieldType.STRING).description("선택지 (A/B)"),
                                fieldWithPath("[].count").type(JsonFieldType.NUMBER).description("선택지에 대한 투표참여 개수")
                        )
                ));
    }

    @Test
    @DisplayName("투표별 연령대에 따른 투표참여 결과 조회")
    public void getBallotsByAge() throws Exception {
        List<BallotDto.CountByAge> response = new ArrayList<>();
        for(int i = 1; i <= 6; i++) {
            response.add(new BallotDto.CountByAge(10 * i, "A", 2L));
            response.add(new BallotDto.CountByAge(10 * i, "B", 1L));
        }
        given(ballotService.getCountByAge(any(), any())).willReturn(response);

        ResultActions results = mvc.perform(get("/api/ballots/votes/{voteId}/age", 1L));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("ballots_countByAge",
                        pathParameters(
                                parameterWithName("voteId").description("투표 식별자")
                        ),
                        responseFields(
                                fieldWithPath("[].age").type(JsonFieldType.NUMBER).description("연령대 (10/20/30/40/50/60)"),
                                fieldWithPath("[].choice").type(JsonFieldType.STRING).description("선택지 (A/B)"),
                                fieldWithPath("[].count").type(JsonFieldType.NUMBER).description("선택지에 대한 투표참여 개수")
                        )
                ));
    }
}
