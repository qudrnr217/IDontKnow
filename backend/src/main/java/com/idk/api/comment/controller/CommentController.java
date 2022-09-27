package com.idk.api.comment.controller;

import com.idk.api.comment.dto.CommentRequest;
import com.idk.api.comment.dto.CommentResponse;
import com.idk.api.comment.service.CommentService;
import com.idk.api.user.security.userdetails.CurrentUser;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponse.OnlyId> create(@RequestBody CommentRequest.Create request, @CurrentUser CustomUserDetails customUserDetails){
        CommentResponse.OnlyId response = commentService.create(request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse.GetOne> getComment(@PathVariable Long commentId, @CurrentUser CustomUserDetails customUserDetails) {
        CommentResponse.GetOne response = commentService.getOne(commentId, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponse.OnlyId> update(@PathVariable Long commentId, @RequestBody CommentRequest.Update request, @CurrentUser CustomUserDetails customUserDetails) {
        CommentResponse.OnlyId response = commentService.update(commentId, request, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResponse.OnlyId> delete(@PathVariable Long commentId, @CurrentUser CustomUserDetails customUserDetails){
        CommentResponse.OnlyId response = commentService.delete(commentId, customUserDetails.getUser());
        return ResponseEntity.ok().body(response);
    }
}
