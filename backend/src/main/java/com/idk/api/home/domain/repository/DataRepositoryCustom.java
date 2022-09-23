package com.idk.api.home.domain.repository;

import com.idk.api.home.domain.entity.Data;

import java.util.List;

public interface DataRepositoryCustom {
    List<Data> searchMenusAtThisTimeInAddress(Integer addressId);
}
