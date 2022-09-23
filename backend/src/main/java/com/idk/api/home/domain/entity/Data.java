package com.idk.api.home.domain.entity;

import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.menucode.domain.entity.MenuCode;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "data")
public class Data implements Serializable {

    @EmbeddedId
    private DataId id;

    @Column(name = "order_quantity")
    private Long orderQuantity;

    @MapsId("districtId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private DistrictCode districtCode;

    @MapsId("menuId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuCode menuCode;

}
