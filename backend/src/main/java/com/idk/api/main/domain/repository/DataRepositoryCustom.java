package com.idk.api.main.domain.repository;

import com.idk.api.main.domain.entity.Data;

import java.util.List;

public interface DataRepositoryCustom {
    List<Data> searchMenusAtThisTimeInAddress(Integer addressId);
}
