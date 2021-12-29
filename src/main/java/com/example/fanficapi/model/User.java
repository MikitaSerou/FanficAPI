package com.example.fanficapi.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users") //bcs user - keyword for Postgres
@Getter
@Setter
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 3, max = 20)
    @Column(unique = true, nullable = false)
    @NonNull

    String username;

    @Email
    @Column(unique = true, nullable = false)
    @NonNull

    String email;

    @Size(min = 8)
    @Column(nullable = false)
    @NonNull
    String password;

    @Column(name = "birth_date", nullable = false)
    @NonNull
    LocalDate birthDate;

    @Column(name = "registration_date", nullable = false)
    @NonNull
    LocalDate registrationDate;

    @ManyToMany(fetch = FetchType.EAGER)
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

    public User(@Size(min = 3, max = 20) @NonNull String username, @Email @NonNull String email, @Size(min = 8, max = 40) @NonNull String password, @NonNull LocalDate birthDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.registrationDate = LocalDate.now();
    }

}
