package com.spring.app.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import lombok.*;


@Entity
@Table(name = "Role")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private Integer id;

    @Column(name = "role_name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_permission"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}
