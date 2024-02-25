package com.fishingclub.main.entities;

import com.fishingclub.main.enums.IdentityDocumentType;
import com.fishingclub.main.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole userRole;

    @Column(nullable = false)
    private LocalDate accessionDate;

    @Column(nullable = false)
    private String nationality;

    @Enumerated
    @Column(nullable = false)
    private IdentityDocumentType identityDocument;

    @Column(nullable = false)
    private String identityNumber;

    @Column(nullable = false)
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Hunting> huntings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userRole.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
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
        return true;
    }
}
