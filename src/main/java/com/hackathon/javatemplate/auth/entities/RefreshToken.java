package com.hackathon.javatemplate.auth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    private String refreshToken;

    @Column(nullable = false)
    private Instant expiry;

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String accessToken;

}
