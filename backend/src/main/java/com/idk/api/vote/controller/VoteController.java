package com.idk.api.vote.controller;


import com.idk.api.user.security.userdetails.CurrentUser;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.dto.VoteRequest;
import com.idk.api.vote.dto.VoteResponse;
import com.idk.api.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<VoteResponse.OnlyId> create(@RequestBody VoteRequest.Create request, @CurrentUser CustomUserDetails customUserDetails) {
        VoteResponse.OnlyId response = voteService.create(request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }
}
