package com.idk.api.user.controller;

import com.idk.api.user.dto.UserRequest;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

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
    public ResponseEntity<UserResponse.Login> login(@RequestBody UserRequest.Login request){
        return ResponseEntity.ok().body(userService.login(request));
    }

    @PatchMapping("/pw")
    public ResponseEntity<Boolean> resetPassword(@RequestBody UserRequest.RePassword request){
        return ResponseEntity.ok().body(userService.resetPassword(request));
    }
}