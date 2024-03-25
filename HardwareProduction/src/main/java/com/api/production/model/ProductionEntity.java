package com.api.production.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "Production")
@NamedQuery(name = "ProductionEntity.findOne", query = "SELECT new com.api.production.wrapper.ProductionWrapper(p.production_id, p.production_date, p.quantity) from ProductionEntity p where p.production_id=:id")
@NamedQuery(name = "ProductionEntity.getProductions", query = "SELECT new com.api.production.wrapper.ProductionWrapper(p.production_id, p.production_date, p.quantity) from ProductionEntity p")
public class ProductionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer production_id;

    @ManyToOne
    @JoinColumn(name = "hardware_id", nullable = false)
    private HardwareEntity hardwareEntity;

    @NonNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date production_date;

    @Min(value = 0L)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity regionEntity;
}
