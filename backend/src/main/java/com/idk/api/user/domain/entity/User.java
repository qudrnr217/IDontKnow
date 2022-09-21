package com.idk.api.user.domain.entity;

import com.idk.api.common.entity.BaseEntity;
import com.idk.api.districtcode.domain.entity.DistrictCode;
import com.idk.api.user.domain.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String email;

    @Column(name = "nickname")
    private String name;

    private String password;

    private String gender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private DistrictCode districtCode;

    private int age;

    @Column(name = "refresh_token")
    private String refreshToken;
    /*
    @OneToMany(mappedBy = "user")
    private List<Ballot> ballots = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Report> reports = new ArrayList<>();
    */
}
