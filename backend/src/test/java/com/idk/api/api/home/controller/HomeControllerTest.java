package com.idk.api.api.home.controller;

import com.idk.api.MvcTest;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.home.controller.HomeController;
import com.idk.api.home.domain.entity.Data;
import com.idk.api.home.domain.entity.DataId;
import com.idk.api.home.domain.entity.Hotspot;
import com.idk.api.home.dto.HomeResponse;
import com.idk.api.home.service.HomeService;
import com.idk.api.menucode.domain.entity.MenuCode;
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
import java.time.LocalTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Home API 문서화")
@WebMvcTest(HomeController.class)
class HomeControllerTest extends MvcTest {

    @MockBean
    private HomeService homeService;
    private Data datum1, datum2;
    private DataId dataId1, dataId2;
    private DistrictCode districtCode;
    private MenuCode menuCode1, menuCode2;
    private Hotspot hotspot;

    @BeforeEach
    public void setUp() {
        dataId1 = DataId.builder()
                .districtId(1)
                .menuId(1)
                .time(LocalTime.of(12, 0, 0))
                .build();
        dataId2 = DataId.builder()
                .districtId(1)
                .menuId(6)
                .time(LocalTime.of(12, 0, 0))
                .build();
        districtCode = DistrictCode.builder()
                .id(1)
                .name("강남구")
                .build();
        menuCode1 = MenuCode.builder()
                .id(1)
                .name("한식")
                .build();
        menuCode2 = MenuCode.builder()
                .id(6)
                .name("치킨")
                .build();
        datum1 = Data.builder()
                .id(dataId1)
                .orderQuantity(256L)
                .districtCode(districtCode)
                .menuCode(menuCode1)
                .build();
        datum2 = Data.builder()
                .id(dataId2)
                .orderQuantity(128L)
                .districtCode(districtCode)
                .menuCode(menuCode2)
                .build();
        hotspot = Hotspot.builder()
                .id(1L)
                .firstPlace("강남 MICE 관광특구")
                .secondPlace("잠실 관광특구")
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("현재시각_및_지역구_인기_메뉴_조회")
    public void getBestMenus() throws Exception {
        List<HomeResponse.Menu> response = List.of(HomeResponse.Menu.build(datum1), HomeResponse.Menu.build(datum2));
        String request = String.valueOf(1);
        given(homeService.getBestMenusAtThisTimeInAddress(1)).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/data/menu")
                .queryParam("districtId", request)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        );
        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-menus",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("districtId").description("서울 지역구 코드")
                        ),
                        responseFields(
                                fieldWithPath("[].menuId").type(JsonFieldType.NUMBER).description("메뉴 코드"),
                                fieldWithPath("[].menuName").type(JsonFieldType.STRING).description("메뉴 카테고리 이름"),
                                fieldWithPath("[].orderQuantity").type(JsonFieldType.NUMBER).description("주문 건수")
                        )
                ));
        verify(homeService).getBestMenusAtThisTimeInAddress(1);
    }

    @Test
    @DisplayName("현재시각_서울_인기장소_조회")
    public void getHotspots() throws Exception {
        HomeResponse.Hotspot response = HomeResponse.Hotspot.build(hotspot);
        given(homeService.getBestHotspotsAtThisTime()).willReturn(response);
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders.get("/api/data/hotspot")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"));
        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-hotspots",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("placeA").type(JsonFieldType.STRING).description("현 시각 서울 인기 장소 A"),
                                fieldWithPath("placeB").type(JsonFieldType.STRING).description("현 시각 서울 인기 장소 B"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("갱신 시각")
                        )
                ));
        verify(homeService).getBestHotspotsAtThisTime();

    }
}