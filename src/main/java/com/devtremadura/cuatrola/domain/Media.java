package com.devtremadura.cuatrola.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "media")
public class Media {

    @Id
    @Column
    private Long id;

    @Column
    private byte[] data;

    @Column
    private String type;

    @OneToOne(mappedBy = "media")
    private User user;
}
