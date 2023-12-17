package com.WorkSphere.WorkSphere.DAOs.token;

import com.WorkSphere.WorkSphere.DAOs.UserEntity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tokens")
public class Token {

    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_sequence"
    )
    @Column(name ="id", unique = true , nullable = false)
    private long id;

    @Column(name ="token" , unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked ,expired;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;


    public Token(String token, TokenType tokenType, boolean revoked, boolean expired, UserEntity userEntity) {
        this.token = token;
        this.tokenType = tokenType;
        this.revoked = revoked;
        this.expired = expired;
        this.userEntity = userEntity;
    }
}