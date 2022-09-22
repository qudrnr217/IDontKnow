package com.idk.api.vote.domain.entity;

import com.idk.api.common.entity.BaseEntity;
import com.idk.api.user.domain.entity.User;
import com.idk.api.vote.dto.BallotRequest;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "ballot")
public class Ballot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ballot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @Column(nullable = false)
    private String choice;

    public static Ballot create(BallotRequest.Create request, Vote vote, User user) {
        return Ballot.builder()
                .user(user)
                .vote(vote)
                .choice(request.getChoice())
                .build();
    }
}
