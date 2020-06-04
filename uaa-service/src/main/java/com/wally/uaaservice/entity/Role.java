package com.wally.uaaservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@ToString
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
