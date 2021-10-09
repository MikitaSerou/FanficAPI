package com.example.fanficapi.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users") //bcs user - keyword for Postgres
@Getter
@Setter
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 20)
    @Column(unique = true)
    @NonNull
    private String username;

    @Size(max = 50)
    @Email
    @Column(unique = true)
    @NonNull
    private String email;

    @Size(max = 120)
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "preferences",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @ToString.Exclude
    private Set<Theme> preferences = new HashSet<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Publication> publications;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id"))
    @ToString.Exclude
    private Set<Publication> likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bookmarks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id"))
    @ToString.Exclude
    private Set<Publication> bookmarks;

    public User(@Size(max = 20) @NonNull String username, @Size(max = 50) @Email @NonNull String email) {
        this.username = username;
        this.email = email;
    }
}
