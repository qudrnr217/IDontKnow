package com.idk.api.menu.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @Column(name = "menu_id")
    private int id;

    @Column(name = "menu_name")
    private String name;

}

