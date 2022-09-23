package com.idk.api.home.controller;

import com.idk.api.home.dto.HomeResponse;
import com.idk.api.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/data")
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/menu")
    public ResponseEntity<List<HomeResponse.Menu>> getBestMenus(
            @RequestParam(value = "districtId", required = false) Integer districtId){
        if (districtId == null) districtId = 1;
        return ResponseEntity.ok().body(homeService.getBestMenusAtThisTimeInAddress(districtId));
    }
}