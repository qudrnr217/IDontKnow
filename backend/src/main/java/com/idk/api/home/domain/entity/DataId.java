package com.idk.api.home.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalTime;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
@EqualsAndHashCode
public class DataId implements Serializable {

    @Column(name = "district_id")
    private int districtId;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "time")
    private LocalTime time;

}
