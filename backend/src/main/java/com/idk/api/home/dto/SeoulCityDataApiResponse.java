package com.idk.api.home.dto;

import com.idk.api.home.domain.CongestionLevel;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SeoulCityDataApiResponse implements Comparable<SeoulCityDataApiResponse> {

    private String areaName;
    private CongestionLevel areaCongestionLevel;
    private int areaPopulation;
    private float nonResidentPopulationRate;

    public static SeoulCityDataApiResponse build(String areaName,
                                                 CongestionLevel areaCongestionLevel,
                                                 int areaPopulation,
                                                 float nonResidentPopulationRate) {

        return SeoulCityDataApiResponse.builder()
                .areaName(areaName)
                .areaCongestionLevel(areaCongestionLevel)
                .areaPopulation(areaPopulation)
                .nonResidentPopulationRate(nonResidentPopulationRate)
                .build();
    }

    @Override
    public int compareTo(SeoulCityDataApiResponse o) {
        if (this.areaCongestionLevel.getLevel() > o.areaCongestionLevel.getLevel()) return -1;
        else if (this.areaCongestionLevel.getLevel() < o.areaCongestionLevel.getLevel()) return 1;
        else {
            if (this.areaPopulation > o.areaPopulation) return -1;
            else if (this.areaPopulation < o.areaPopulation) return 1;
            else {
                if (this.nonResidentPopulationRate > o.nonResidentPopulationRate) return -1;
                else if (this.nonResidentPopulationRate < o.nonResidentPopulationRate) return 1;
                else return 0;
            }
        }
    }
}
