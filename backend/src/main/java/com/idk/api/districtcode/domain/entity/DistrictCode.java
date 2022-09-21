package com.idk.api.districtcode.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "district_code")
public class DistrictCode {
    @Id
    @Column(name = "district_id")
    private int id;

    @Column(name = "district_name")
    private String name;


}
