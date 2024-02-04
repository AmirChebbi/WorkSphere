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
@Table
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname" ,nullable = false)
    private String lastName;

    @Column(name = "Email" ,nullable = false, unique = true)
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
    @JoinColumn(name = "role_name",referencedColumnName = "name")
    private Role role;

    public UserEntity(String firstName, String lastName, String email, String phoneNumber, String password, Date creationDate, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.rating = (double) 0;
        this.creationDate = creationDate;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

}
