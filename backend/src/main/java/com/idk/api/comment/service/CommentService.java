package com.idk.api.comment.service;

import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.comment.domain.repository.CommentRepository;
import com.idk.api.comment.dto.CommentRequest;
import com.idk.api.comment.dto.CommentResponse;
import com.idk.api.comment.exception.CommentDeletedException;
import com.idk.api.comment.exception.CommentNotFoundException;
import com.idk.api.common.exception.PermissionException;
import com.idk.api.user.domain.Role;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.domain.repository.VoteRepository;
import com.idk.api.vote.exception.VoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponse.OnlyId create(CommentRequest.Create request, User user) {
        Vote vote = voteRepository.findById(request.getVoteId()).orElseThrow(VoteNotFoundException::new);
        Comment comment = Comment.create(request, vote, user);
        Comment savedComment = commentRepository.save(comment);
        return CommentResponse.OnlyId.build(savedComment);
    }

    @Transactional
    public CommentResponse.OnlyId update(Long commentId, CommentRequest.Update request, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if(!Objects.equals(user.getId(), comment.getUser().getId())) throw new PermissionException();
        comment.update(request);
        return CommentResponse.OnlyId.build(comment);
    }

    public CommentResponse.GetOne getOne(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if(!Objects.equals(user.getId(), comment.getUser().getId())) throw new PermissionException();
        if(comment.getDeletedAt() != null) throw new CommentDeletedException();
        return CommentResponse.GetOne.build(comment.getVote().getUser().getId(), comment);
    }

    @Transactional
    public CommentResponse.OnlyId delete(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        if(!Objects.equals(user.getId(), comment.getUser().getId())) throw new PermissionException();
        if(comment.getDeletedAt() != null) throw new CommentDeletedException();
        comment.delete(user.getRole().equals(Role.ADMIN));
        return CommentResponse.OnlyId.build(comment);
    }
}
