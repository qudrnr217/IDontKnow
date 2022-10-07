package com.idk.api.vote.domain.entity;

import com.idk.api.comment.domain.entity.Comment;
import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.common.entity.BaseEntity;
import com.idk.api.user.domain.entity.User;
import com.idk.api.user.security.userdetails.CustomUserDetails;
import com.idk.api.vote.dto.VoteRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "vote")
public class Vote extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, name = "option_A")
    private String optionA;

    @Column(nullable = false, name = "option_B")
    private String optionB;

    private boolean status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "sub_category")
    private SubCategory subCategory;

    @Column(name = "a_count")
    private int aCount;

    @Column(name = "b_count")
    private int bCount;

    @Column(name = "hit_count")
    private int hitCount;

    @Column(name = "comment_count")
    private int commentCount;

    @Column(name = "admin_deleted")
    private boolean adminDeleted;

    private String result;

    @Builder.Default
    @OneToMany(mappedBy = "vote", cascade = CascadeType.REMOVE)
    private List<Ballot> ballotList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "vote", cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();

    public static Vote create(VoteRequest.Create request, User user) {
        return Vote.builder()
                .user(user)
                .title(request.getTitle())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .status(false)
                .category(Category.fromString(request.getCategory()))
                .subCategory(SubCategory.fromString(request.getSubCategory()))
                .build();

    }

    public Ballot getVoted(CustomUserDetails customUserDetails) {
        Optional<Ballot> ballot = this.ballotList.stream()
                .filter(b -> b.getUser().getId().equals(customUserDetails.getUser().getId())).findFirst();
        if(customUserDetails != null && ballot.isPresent()) {
            return ballot.get();
        }
        return null;
    }

    public void changeStatus(VoteRequest.ChangeStatus request, int countA, int countB) {
        this.status = request.isStatus();
        if(countA == countB){
            this.result = "E";
        }else if(countA > countB){
            this.result = "A";
        }else {
            this.result = "B";
        }
    }

    public void hit() {
        this.hitCount++;
    }
    public void addComment() {
        this.commentCount++;
    }
    public void deleteComment() {
        this.commentCount--;
    }
    public void changeBallotCount(boolean isDelete, String voted) {
        if(isDelete){
            switch(voted) {
                case "A":
                    this.aCount--;
                    break;
                case "B":
                    this.bCount--;
                    break;
            }
        }else {
            switch(voted) {
                case "A":
                    this.aCount++;
                    break;
                case "B":
                    this.bCount++;
                    break;
            }
        }
    }

    public void delete(boolean isAdmin) {
        if(isAdmin){
            this.adminDeleted = true;
        }
        this.setDeletedAt(LocalDateTime.now());
    }
}
