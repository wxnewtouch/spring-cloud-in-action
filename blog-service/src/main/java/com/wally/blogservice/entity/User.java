package com.wally.blogservice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
public class User {

    private Long id;
    private String username;
    private String password;
}
