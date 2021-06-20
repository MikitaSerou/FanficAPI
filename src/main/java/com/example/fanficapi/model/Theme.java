package com.example.fanficapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String imageUrl;

    public Theme(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Publication> publications;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "preferences",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> usersWhoPreference;
}
