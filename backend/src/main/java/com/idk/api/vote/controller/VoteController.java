package com.idk.api.vote.controller;

import com.idk.api.user.security.userdetails.CurrentUser;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    @PostMapping("")
    public ResponseEntity<VoteResponse.OnlyId> create(@RequestBody VoteRequest.Create request, @CurrentUser CustomUserDetails customUserDetails) {
        VoteResponse.OnlyId response = voteService.create(request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<VoteResponse.Info> getOne(@PathVariable Long voteId, @CurrentUser CustomUserDetails customUserDetails) {
        VoteResponse.Info response = voteService.getOne(voteId, customUserDetails);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{voteId}")
    public ResponseEntity<VoteResponse.OnlyId> update(@PathVariable Long voteId, @RequestBody VoteRequest.ChangeStatus request, @CurrentUser CustomUserDetails customUserDetails) {
        VoteResponse.OnlyId response = voteService.changeStatus(voteId, request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<VoteResponse.OnlyId> delete(@PathVariable Long voteId, @CurrentUser CustomUserDetails customUserDetails) {
        VoteResponse.OnlyId response = voteService.delete(voteId, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<VoteResponse.GetOne>> getList(@RequestParam(name = "category") String category, @RequestParam(name = "status") boolean status, @RequestParam(name = "lastVoteId") Long lastVoteId) {
        Page<VoteResponse.GetOne> response = voteService.getList(lastVoteId, category, status);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<VoteResponse.GetOne>> getTop3(@RequestParam(name = "category") String category) {
        List<VoteResponse.GetOne> response = voteService.getTop3(category);
        return ResponseEntity.ok().body(response);
    }
}
