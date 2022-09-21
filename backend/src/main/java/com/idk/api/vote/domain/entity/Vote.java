package com.idk.api.vote.domain.entity;

import com.idk.api.common.category.Category;
import com.idk.api.common.category.SubCategory;
import com.idk.api.common.entity.BaseEntity;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.dto.VoteRequest;
import lombok.*;

import javax.persistence.*;

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
    private User author;

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

    public static Vote create(VoteRequest.Create request, User user) {
        return Vote.builder()
                .author(user)
                .title(request.getTitle())
                .optionA(request.getOptionA())
                .optionB(request.getOptionB())
                .status(false)
                .category(request.getCategory())
                .subCategory(request.getSubCategory())
                .build();

    }

}
