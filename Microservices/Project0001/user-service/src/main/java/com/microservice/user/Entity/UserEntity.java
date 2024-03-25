package com.microservice.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class UserEntity {
    @Id
    @Column(name = "idUser")
    private String userId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 60)
    private String email;

    @Column(name = "description", length = 150)
    private String description;

    @Transient
    private List<Calification> califications = new ArrayList<>();
}
