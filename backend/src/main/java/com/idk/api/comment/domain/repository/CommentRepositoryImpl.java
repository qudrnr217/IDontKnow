package com.idk.api.comment.domain.repository;

import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.vote.domain.entity.Vote;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.idk.api.comment.domain.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Comment> findAllByVoteOrderByAsc(Vote vote) {
        return jpaQueryFactory.selectFrom(comment)
                .where(comment.vote.id.eq(vote.getId()),
                        comment.deletedAt.isNull())
                .orderBy(comment.id.asc())
                .fetch();
    }
}
