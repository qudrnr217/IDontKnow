package com.idk.api.home.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "hotspot")
public class Hotspot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotspot_id")
    private Long id;

    @Column(name = "first_place")
    private String firstPlace;

    @Column(name = "second_place")
    private String secondPlace;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static Hotspot create(String firstPlace, String secondPlace) {
        return Hotspot.builder()
                .firstPlace(firstPlace)
                .secondPlace(secondPlace)
                .createdAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")))
                .build();

    }
}
