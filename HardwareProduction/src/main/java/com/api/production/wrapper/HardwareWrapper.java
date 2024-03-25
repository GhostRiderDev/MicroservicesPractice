package com.api.production.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HardwareWrapper {
    private Integer id;
    private Double unit_cost;
    private String name;
    private String category;
    private String image;
}
