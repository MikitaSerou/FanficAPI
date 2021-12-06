package com.example.fanficapi.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(max = 20)
    @Column(unique = true)
    @NonNull
    String username;

    @Size(max = 50)
    @Email
    @Column(unique = true)
    @NonNull
    String email;

    @Size(max = 120)
    @NonNull
    String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "preferences",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    @ToString.Exclude
    Set<Theme> preferences = new HashSet<>();

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    Set<Publication> publications;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id"))
    @ToString.Exclude
    Set<Publication> likes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bookmarks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "publication_id"))
    @ToString.Exclude
    Set<Publication> bookmarks;

    public User(@Size(max = 20) @NonNull String username, @Size(max = 50) @Email @NonNull String email) {
        this.username = username;
        this.email = email;
    }
}
