package com.WorkSphere.WorkSphere.DAOs.UserEntity;

import com.WorkSphere.WorkSphere.DAOs.Role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private UUID id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname" ,nullable = false)
    private String lastName;

    @Column(name = "email" ,nullable = false)
    private String email;

    @Column(name = "phone_number", unique = true , nullable = false)
    private String phoneNumber;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "creating_date" , nullable = false)
    private Date creationDate;

    @Column( name = "is_enabled", nullable = false)
    private boolean isEnabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
