package com.example.fanficapi.security.model;

import com.example.fanficapi.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Table(name = "refresh_token")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Token value is null")
    @Column(nullable = false, unique = true)
    String token;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @NotNull(message = "Expiration date is null")
    @Column(nullable = false)
    Instant expirationDate;
}
