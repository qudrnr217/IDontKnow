package com.idk.api.home.scheduler;

import com.idk.api.home.domain.CongestionLevel;
import com.idk.api.home.domain.entity.Hotspot;
import com.idk.api.home.domain.repository.HotspotRepository;
import com.idk.api.home.dto.SeoulCityDataApiResponse;
import io.netty.handler.timeout.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FetchSeoulCityDataScheduler {
    @Value("${SEOUL_CITY_DATA.OPEN_API_KEY}")
    private String seoulCityDataOpenApiKey;
    @Value("${SEOUL_CITY_DATA.BASE_URL}")
    private String baseUrl;

    private static final String[] areaNames = {
            "강남 MICE 관광특구",
            "동대문 관광특구",
            "명동 관광특구",
            "이태원 관광특구",
            "잠실 관광특구",
            "종로·청계 관광특구",
            "홍대 관광특구",
            "경복궁·서촌마을",
            "광화문·덕수궁",
            "창덕궁·종묘",
            "DMC(디지털미디어시티)",
            "창동 신경제 중심지",
            "노량진",
            "낙산공원·이화마을",
            "북촌한옥마을",
            "가로수길",
            "성수카페거리",
            "수유리 먹자골목",
            "쌍문동 맛집거리",
            "압구정로데오거리",
            "여의도",
            "영등포 타임스퀘어",
            "인사동·익선동",
            "국립중앙박물관·용산가족공원",
            "남산공원",
            "뚝섬한강공원",
            "망원한강공원",
            "반포한강공원",
            "북서울꿈의숲",
            "서울대공원",
            "서울숲공원",
            "월드컵공원",
            "이촌한강공원",
            "잠실종합운동장",
            "잠실한강공원"
    };

    private final HotspotRepository hotspotRepository;
    private List<SeoulCityDataApiResponse> seoulCityDataApiResponseList = new ArrayList<>();


    // 10분 마다 실행
    @Scheduled(fixedDelay = 60 * 10 * 1000L)
    public void executeFetchPopularLocation() {

        seoulCityDataApiResponseList.clear();

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(32 * 1024 * 1024))
                .build();

        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
                .exchangeStrategies(strategies)
                .build();

        for (String areaName : areaNames) {
            try {
                CongestionLevel areaCongestionLevel;
                int areaPopulationMin;
                int areaPopulationMax;
                int areaPopulationAvg;
                float nonResidentPopulationRate;

                String xml = webClient.get()
                        .uri("/{seoulCityDataOpenApiKey}/xml/citydata/1/1/{areaName}", seoulCityDataOpenApiKey, areaName)
                        .retrieve()
                        .bodyToMono(String.class)
                        .timeout(Duration.ofSeconds(30))
                        .block();

                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new InputSource(new StringReader(xml)));
                document.getDocumentElement().normalize();

                NodeList citydata = document.getElementsByTagName("CITYDATA").item(0).getChildNodes();
                if ("".equals(citydata.item(0).getFirstChild().getNodeValue())) continue;
                areaName = citydata.item(0).getFirstChild().getNodeValue();

                NodeList livePopulationStats = citydata.item(1).getFirstChild().getChildNodes();
                if ("".equals(livePopulationStats.item(0).getFirstChild().getNodeValue())) continue;
                switch (livePopulationStats.item(0).getFirstChild().getNodeValue()) {
                    case "여유":
                        areaCongestionLevel = CongestionLevel.여유;
                        break;
                    case "보통":
                        areaCongestionLevel = CongestionLevel.보통;
                        break;
                    case "붐빔":
                        areaCongestionLevel = CongestionLevel.붐빔;
                        break;
                    case "매우 붐빔":
                        areaCongestionLevel = CongestionLevel.매우_붐빔;
                        break;
                    default:
                        continue;
                }

                if ("".equals(livePopulationStats.item(2).getFirstChild().getNodeValue())) continue;
                areaPopulationMin = Integer.parseInt(livePopulationStats.item(2).getFirstChild().getNodeValue());

                if ("".equals(livePopulationStats.item(3).getFirstChild().getNodeValue())) continue;
                areaPopulationMax = Integer.parseInt(livePopulationStats.item(3).getFirstChild().getNodeValue());

                areaPopulationAvg = (areaPopulationMax + areaPopulationMin) / 2;

                if ("".equals(livePopulationStats.item(4).getFirstChild().getNodeValue())) continue;
                nonResidentPopulationRate = Float.parseFloat(livePopulationStats.item(4).getFirstChild().getNodeValue());

                log.info("{}, {}, {}, {}", areaName, areaCongestionLevel, areaPopulationAvg, nonResidentPopulationRate);
                seoulCityDataApiResponseList.add(SeoulCityDataApiResponse.build(areaName, areaCongestionLevel, areaPopulationAvg, nonResidentPopulationRate));

            } catch (TimeoutException ex) {
                log.warn("서울 실시간 도시데이터 / {}번 핫스팟 API Response Timeout", areaName);
            } catch (WebClientException ex) {
                log.warn("서울 실시간 도시데이터 / {}번 핫스팟 API Connection Timeout", areaName);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                log.warn("서울 실시간 도시데이터 / {}번 핫스팟 XML Parsing Error", areaName);
            }
        }


        if (seoulCityDataApiResponseList.size() < 2) return;

        seoulCityDataApiResponseList.sort(Comparator.naturalOrder());
        String placeA = seoulCityDataApiResponseList.get(0).getAreaName();
        String placeB = seoulCityDataApiResponseList.get(1).getAreaName();
        Hotspot hotspot = Hotspot.create(placeA, placeB);

        hotspotRepository.save(hotspot);
    }
}
