package com.spring.app.entity;
import jakarta.persistence.*;
import  lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    private Boolean isEnable;
    @Column(name = "account_no_expired")
    private Boolean accountNoExpired;
    @Column(name =  "account_no_locked")
    private Boolean accountNoLocked;
    @Column(name = "credential_no_expired")
    private Boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<RoleEntity> roles = new HashSet<>();

}
