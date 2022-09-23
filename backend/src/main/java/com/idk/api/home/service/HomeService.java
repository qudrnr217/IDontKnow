package com.idk.api.home.service;

import com.idk.api.home.domain.repository.DataRepository;
import com.idk.api.home.domain.entity.Data;
import com.idk.api.home.dto.HomeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HomeService {

    private final DataRepository dataRepository;

    public List<HomeResponse.Menu> getBestMenusAtThisTimeInAddress(Integer addressId) {
        List<Data> bestMenus = dataRepository.searchMenusAtThisTimeInAddress(addressId);
        List<HomeResponse.Menu> response = new ArrayList<>();
        for (Data datum :bestMenus) response.add(HomeResponse.Menu.build(datum));
        return response;
    }
}
