package com.idk.api.user.domain.controller;

import com.idk.api.user.domain.dto.UserRequest;
import com.idk.api.user.domain.dto.UserResponse;
import com.idk.api.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponse.OnlyId> join(@RequestBody UserRequest.Join request){
        return ResponseEntity.ok().body(userService.join(request));
    }

    @GetMapping("/join/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkEmailDuplication(email));
    }

    @GetMapping("/join/{name}/exists")
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name){
        return ResponseEntity.ok(userService.checkNameDuplication(name));
    }
}