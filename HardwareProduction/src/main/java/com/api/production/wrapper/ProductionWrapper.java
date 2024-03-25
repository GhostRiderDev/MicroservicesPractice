package com.api.production.wrapper;

import com.api.production.model.HardwareEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductionWrapper {

  private Integer production_id;
  private Date production_date;
  private Integer quantity;
}
