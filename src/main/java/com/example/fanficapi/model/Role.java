package com.example.fanficapi.model;

import com.example.fanficapi.enums.RoleName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleName name;

}
