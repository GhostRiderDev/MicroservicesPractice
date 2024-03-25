package com.microservice.user.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Calification {
    private String idCalification;
    private String idUser;
    private String idHotel;
    private int calification;
    private String remark;
    private Hotel hotel;
}
