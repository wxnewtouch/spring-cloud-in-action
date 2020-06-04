package com.wally.userservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userName;
    @Column
    private String passWord;
}
