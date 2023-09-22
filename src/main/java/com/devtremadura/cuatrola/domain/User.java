package com.devtremadura.cuatrola.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String nickname;

    @Column
    private String password; // ??

    @OneToOne(optional = true)
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToOne(mappedBy = "user")
    private Player player;

}
