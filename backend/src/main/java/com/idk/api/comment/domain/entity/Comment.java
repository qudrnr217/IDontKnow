package com.idk.api.comment.domain.entity;

import com.idk.api.comment.dto.CommentRequest;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.common.entity.BaseEntity;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.domain.entity.Vote;
import com.idk.api.vote.dto.VoteRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    private String content;

    @Column(name = "admin_deleted")
    private boolean adminDeleted;

    public static Comment create(CommentRequest.Create request, Vote vote, User user) {
        return Comment.builder()
                .user(user)
                .vote(vote)
                .content(request.getContent())
                .build();
    }

    public void update(CommentRequest.Update request) {
        this.content = request.getContent();
    }

    public void delete(boolean isAdmin) {
        if(isAdmin){
            this.adminDeleted = true;
        }
        this.setDeletedAt(LocalDateTime.now());
    }
}
