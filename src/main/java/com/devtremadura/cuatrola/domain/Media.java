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
@Table(name = "media")
public class Media {

    @Id
    @GeneratedValue(generator = "media_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "media_sequence", sequenceName = "media_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private byte[] data;

    @Column
    private String type;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
