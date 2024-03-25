package com.microservice.user.Entity;

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
public class Hotel {
    private String idHotel;
    private String name;
    private String information;
    private String address;
}

