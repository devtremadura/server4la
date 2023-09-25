package com.devtremadura.cuatrola.domain;

import jakarta.persistence.*;

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
    @GeneratedValue(generator = "users_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String firstname;

    @Column
    private String surname;

    @Column
    private String nickname;

    @OneToOne(mappedBy = "user")
    private Media media;

    @OneToOne(mappedBy = "user")
    private Player player;

}
