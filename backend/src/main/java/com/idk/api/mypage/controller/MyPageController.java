package com.idk.api.mypage.controller;

import com.idk.api.mypage.dto.MyPageRequest;
import com.idk.api.mypage.dto.MyPageResponse;
import com.idk.api.mypage.service.MyPageService;
import com.idk.api.user.dto.UserResponse;
import com.idk.api.user.security.userdetails.CurrentUser;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/mypage/users/")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/{userId}/info")
    public ResponseEntity<MyPageResponse.UserInfo> getUserInfo(@PathVariable("userId") Long userId){
        MyPageResponse.UserInfo response = myPageService.getUserInfo(userId);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{userId}/info")
    public ResponseEntity<MyPageResponse.UserInfo> updateUserInfo(@PathVariable("userId") Long userId, @CurrentUser CustomUserDetails customUserDetails, @RequestBody MyPageRequest.UserInfo request){
        MyPageResponse.UserInfo response = myPageService.updateUserInfo(userId, customUserDetails.getUser(), request);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponse.OnlyId> deleteUserInfo(@PathVariable("userId") Long userId, @CurrentUser CustomUserDetails customUserDetails){
        UserResponse.OnlyId response = myPageService.deleteUserInfo(userId, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{userId}/pw")
    public ResponseEntity<UserResponse.OnlyId> changePassword(@PathVariable("userId") Long userId, @CurrentUser CustomUserDetails customUserDetails, @RequestBody MyPageRequest.UserPassword request){
        UserResponse.OnlyId response = myPageService.updateUserPassword(userId, customUserDetails.getUser(), request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{userId}/votes")
    public ResponseEntity<Page<VoteResponse.GetOne>> getVoteList(@PathVariable("userId") Long userId, @CurrentUser CustomUserDetails customUserDetails, @RequestParam(name = "status") boolean status, @RequestParam(name = "lastVoteId") Long lastVoteId ){
        Page<VoteResponse.GetOne> response = myPageService.getVoteList(userId, lastVoteId, customUserDetails.getUser(), status);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{userId}/ballots")
    public ResponseEntity<Page<VoteResponse.GetOne>> getBallotList(@PathVariable("userId") Long userId, @CurrentUser CustomUserDetails customUserDetails, @RequestParam(name = "status") boolean status, @RequestParam(name = "lastVoteId") Long lastVoteId ){
        Page<VoteResponse.GetOne> response = myPageService.getBallotList(userId, lastVoteId, customUserDetails.getUser(), status);
        return ResponseEntity.ok().body(response);
    }

}
