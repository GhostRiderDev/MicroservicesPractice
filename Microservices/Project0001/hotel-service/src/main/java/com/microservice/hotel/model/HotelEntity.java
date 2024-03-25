package com.microservice.hotel.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Hotel")
@Entity
public class HotelEntity {
    @Id
    private String idHotel;
    @Column(length = 100)
    private String name;
    @Column(length = 150)
    private String information;
    @Column(length = 100)
    private String address;
}
