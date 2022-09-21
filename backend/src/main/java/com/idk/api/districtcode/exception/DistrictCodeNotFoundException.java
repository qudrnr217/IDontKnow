package com.idk.api.districtcode.exception;

import javax.persistence.EntityNotFoundException;

public class DistrictCodeNotFoundException extends EntityNotFoundException {
    public DistrictCodeNotFoundException() {
        super("존재하지 않는 지역구입니다.");
    }
}