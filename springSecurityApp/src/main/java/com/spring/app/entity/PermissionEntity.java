package com.spring.app.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "Permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity {
    @Id
    @Column(name = "id_permission")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, updatable = false)
    private PermissionEnum name;

}
