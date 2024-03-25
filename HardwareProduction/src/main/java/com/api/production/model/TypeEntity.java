package com.api.production.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@Table(name = "Type")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@DynamicUpdate
@DynamicInsert
@NamedQuery(name = "TypeEntity.getType", query = "SELECT new com.api.production.wrapper.TypeWrapper(t.id, t.type_name) from TypeEntity t where t.id=:id")
@NamedQuery(name = "TypeEntity.getAll", query = "SELECT new com.api.production.wrapper.TypeWrapper(t.id,t.type_name) from TypeEntity t")
public class TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer type_id;

    @Column(length = 100, unique = true)
    @NotEmpty
    private String type_name;

    @OneToMany(mappedBy = "typeEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<HardwareEntity> hardwareSet;
}
