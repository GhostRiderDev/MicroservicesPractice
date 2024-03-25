package com.api.production.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "Hardware")
@NamedQuery(name = "HardwareEntity.findOne", query = "SELECT new com.api.production.wrapper.HardwareWrapper(h.hardware_id, h.unit_cost, h.hardware_name, h.typeEntity.type_name, h.image_url) from HardwareEntity h where h.hardware_id=:id")
@NamedQuery(name = "HardwareEntity.getAll", query = "SELECT new com.api.production.wrapper.HardwareWrapper(h.hardware_id, h.unit_cost, h.hardware_name, h.typeEntity.type_name, h.image_url) from HardwareEntity h")
@NamedQuery(name = "HardwareEntity.findByType", query = "SELECT new com.api.production.wrapper.HardwareWrapper(h.hardware_id, h.unit_cost, h.hardware_name, h.typeEntity.type_name, h.image_url) from HardwareEntity h where h.typeEntity.id=:type_id")
@NamedQuery(name = "HardwareEntity.searchPaging", query = "SELECT new com.api.production.wrapper.HardwareWrapper(h.hardware_id, h.unit_cost, h.hardware_name, h.typeEntity.type_name, h.image_url) from HardwareEntity h where h.hardware_name LIKE :keyword")
@NamedQuery(name = "HardwareEntity.totalQuantity", query = "SELECT COUNT(h) from HardwareEntity h")
public class HardwareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hardware_id")
    private Integer hardware_id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeEntity typeEntity;

    @NonNull
    @Min(value = 0L)
    private Double unit_cost;

    @NotEmpty
    @Column(unique = true)
    private String hardware_name;

    @NotEmpty
    private String image_url;

    @OneToMany(mappedBy = "hardwareEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductionEntity> productionSet;
}
