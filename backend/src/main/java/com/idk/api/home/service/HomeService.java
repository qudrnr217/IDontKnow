package com.idk.api.home.service;

import com.idk.api.home.domain.entity.Data;
import com.idk.api.home.domain.entity.Hotspot;
import com.idk.api.home.domain.repository.DataRepository;
import com.idk.api.home.domain.repository.HotspotRepository;
import com.idk.api.home.dto.HomeResponse;
import com.idk.api.home.exception.FetchWeatherApiException;
import com.idk.api.home.exception.HotspotNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class HomeService {

    private final DataRepository dataRepository;
    private final HotspotRepository hotspotRepository;

    @Value("${WEATHER_DATA.OPEN_API_KEY}")
    private String weatherApiKey;

    @Value("${WEATHER_DATA.BASE_URL}")
    private String baseUrl;

    private static final Map<String, String> SKY = new HashMap<>();
    private static final Map<String, String> PTY = new HashMap<>();

    private static final List<Position> POSITIONS = List.of(
            new Position(0, 0),
            new Position(61, 126),
            new Position(62, 126),
            new Position(61, 128),
            new Position(58, 126),
            new Position(59, 125),
            new Position(62, 126),
            new Position(58, 125),
            new Position(59, 124),
            new Position(61, 129),
            new Position(61, 129),
            new Position(61, 127),
            new Position(59, 125),
            new Position(59, 127),
            new Position(59, 127),
            new Position(61, 125),
            new Position(61, 127),
            new Position(61, 127),
            new Position(62, 126),
            new Position(58, 126),
            new Position(58, 126),
            new Position(60, 126),
            new Position(59, 127),
            new Position(60, 127),
            new Position(60, 127),
            new Position(62, 128)
    );

    static {
        SKY.put("1", "맑음");
        SKY.put("2", "구름 조금");
        SKY.put("3", "구름 많음");
        SKY.put("4", "흐림");
        PTY.put("0", "강수 없음");
        PTY.put("1", "비");
        PTY.put("2", "비/눈");
        PTY.put("3", "눈");
        PTY.put("4", "소나기");
    }

    public List<HomeResponse.Menu> getBestMenusAtThisTimeInAddress(Integer districtId) {
        List<Data> bestMenus = dataRepository.searchMenusAtThisTimeInAddress(districtId);
        List<HomeResponse.Menu> response = new ArrayList<>();
        for (Data datum : bestMenus) response.add(HomeResponse.Menu.build(datum));
        return response;
    }

    public HomeResponse.Hotspot getBestHotspotsAtThisTime() {
        Hotspot hotspot = hotspotRepository.findTopByOrderByCreatedAtDesc().orElseThrow(HotspotNotExistsException::new);
        return HomeResponse.Hotspot.build(hotspot);
    }

    public HomeResponse.Weather getTodayWeatherInAddress(Integer districtId) {
        WebClient webClient = WebClient.create(baseUrl);
        Map<String, String> weatherApiResponse = new HashMap<>();

        try {
            String responseBody = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/getVilageFcst")
                            .queryParam("serviceKey", weatherApiKey)
                            .queryParam("pageNo", 1)
                            .queryParam("numOfRows", 14)
                            .queryParam("dataType", "JSON")
                            .queryParam("base_date", LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                            .queryParam("base_time", "0500")
                            .queryParam("nx", POSITIONS.get(districtId).x)
                            .queryParam("ny", POSITIONS.get(districtId).y)
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray item = jsonObject.getJSONObject("response").getJSONObject("body").getJSONObject("items").getJSONArray("item");

            for (int i = 0; i < item.length(); i++) {
                JSONObject el = item.getJSONObject(i);
                weatherApiResponse.put(el.getString("category"), el.getString("fcstValue"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.warn("기상청 단기예보 Open API 예외 발생");
            throw new FetchWeatherApiException();
        }
        return HomeResponse.Weather.build(
                SKY.get(weatherApiResponse.get("SKY")),
                PTY.get(weatherApiResponse.get("PTY")),
                weatherApiResponse.get("POP"),
                weatherApiResponse.get("REH"),
                weatherApiResponse.get("TMP"),
                weatherApiResponse.get("WSD")
        );
    }

    @RequiredArgsConstructor
    private static class Position {
        private final int x, y;
    }
}
