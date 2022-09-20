package com.idk.api.user.domain.controller;

import com.idk.api.user.domain.dto.UserRequest;
import com.idk.api.user.domain.dto.UserResponse;
import com.idk.api.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponse.UserInfoOnlyId> join(@RequestBody UserRequest.Join request){
        UserResponse.UserInfoOnlyId response = userService.join(request);
        return ResponseEntity.ok().body(response);
    }
}