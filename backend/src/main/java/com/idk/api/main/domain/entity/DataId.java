package com.idk.api.main.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@EqualsAndHashCode
public class DataId implements Serializable {

    @Column(name = "district_id")
    private int districtId;

    @Column(name = "menu_id")
    private int menuId;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

}
