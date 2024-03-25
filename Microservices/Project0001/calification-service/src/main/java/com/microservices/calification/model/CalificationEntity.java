package com.microservices.calification.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Calification")
public class CalificationEntity {
    @Id
    private String idCalification;
    private String idUser;
    private String idHotel;
    private int calification;
    private String remark;

}
