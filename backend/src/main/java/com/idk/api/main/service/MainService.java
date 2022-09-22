package com.idk.api.main.service;

import com.idk.api.main.domain.entity.Data;
import com.idk.api.main.domain.repository.DataRepository;
import com.idk.api.main.dto.MainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MainService {

    private final DataRepository dataRepository;

    public List<MainResponse.Menu> getBestMenusAtThisTimeInAddress(Integer addressId) {
        List<Data> bestMenus = dataRepository.searchMenusAtThisTimeInAddress(addressId);
        List<MainResponse.Menu> response = new ArrayList<>();
        for (Data datum :bestMenus) response.add(MainResponse.Menu.build(datum));
        return response;
    }
}
