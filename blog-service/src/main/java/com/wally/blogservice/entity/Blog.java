package com.wally.blogservice.entity;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
public class Blog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column
    private String title;

    @Column
    private String suject;
}
