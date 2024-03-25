package com.api.production.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "Region")
@NamedQuery(name = "RegionEntity.findOne", query = "SELECT new com.api.production.wrapper.RegionWrapper(r.region_id, r.region_name) from RegionEntity r where r.region_id=:id")
@NamedQuery(name = "RegionEntity.getRegions", query = "SELECT new com.api.production.wrapper.RegionWrapper(r.region_id, r.region_name) from RegionEntity r")
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer region_id;

    @OneToMany(mappedBy = "regionEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductionEntity> productionSet;

    @NonNull
    @NotEmpty
    @Column(unique = true)
    private String region_name;
}
