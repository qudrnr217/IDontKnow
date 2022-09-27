package com.idk.api.comment.domain.repository;

import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.vote.domain.entity.Vote;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findAllByVoteOrderByAsc(Vote vote);
}
