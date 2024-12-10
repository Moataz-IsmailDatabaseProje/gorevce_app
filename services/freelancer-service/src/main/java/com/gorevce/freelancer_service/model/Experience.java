package com.gorevce.freelancer_service.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "experiences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experience {
    @Id
    private String id;
    private String title;
    private String company;
    private String city;
    private Date startDate;
    private Date endDate;
    private String description;

}
