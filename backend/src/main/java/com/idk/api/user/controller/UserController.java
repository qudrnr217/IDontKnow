package com.idk.api.user.controller;

import com.idk.api.common.utils.CookieUtils;
import com.idk.api.user.dto.UserRequest;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final String ACCESS_TOKEN = "access_token";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final Integer MAX_COOKIE_TIME_S = 7 * 24 * 60 * 60;

    @PostMapping("/join")
    public ResponseEntity<UserResponse.OnlyId> join(@RequestBody UserRequest.Join request){
        return ResponseEntity.ok().body(userService.join(request));
    }

    @GetMapping("/email/{email}/exist")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkEmailDuplication(email));
    }

    @GetMapping("/name/{name}/exist")
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name){
        return ResponseEntity.ok(userService.checkNameDuplication(name));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request, HttpServletResponse response){
        UserResponse.LoginWithToken tokenResponse = userService.login(request);
        UserResponse.Login finalResponse = UserResponse.Login.builder()
                        .userId(tokenResponse.getUserId())
                        .name(tokenResponse.getName())
                        .districtId(tokenResponse.getDistrictId())
                        .build();

        CookieUtils.addCookie(response, ACCESS_TOKEN, tokenResponse.getAccessToken(), MAX_COOKIE_TIME_S);
        CookieUtils.addCookie(response, REFRESH_TOKEN, tokenResponse.getRefreshToken(), MAX_COOKIE_TIME_S);

        return ResponseEntity.ok().body(finalResponse);
    }

    @PatchMapping("/pw")
    public ResponseEntity<Boolean> resetPassword(@RequestBody UserRequest.RePassword request){
        return ResponseEntity.ok().body(userService.resetPassword(request));
    }
}