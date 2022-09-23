package com.idk.api.vote.controller;

import com.idk.api.user.security.userdetails.CurrentUser;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.dto.BallotRequest;
import com.idk.api.vote.dto.BallotResponse;
import com.idk.api.vote.service.BallotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ballots")
public class BallotController {
    private final BallotService ballotService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BallotResponse.OnlyId> create(@RequestBody BallotRequest.Create request, @CurrentUser CustomUserDetails customUserDetails){
        BallotResponse.OnlyId response = ballotService.create(request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{ballotId}")
    public ResponseEntity<BallotResponse.OnlyId> delete(@PathVariable Long ballotId, @CurrentUser CustomUserDetails customUserDetails) {
        BallotResponse.OnlyId response = ballotService.delete(ballotId, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }
}
