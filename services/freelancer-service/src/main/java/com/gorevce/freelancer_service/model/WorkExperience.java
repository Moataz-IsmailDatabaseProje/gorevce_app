package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "work_experience")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperience {
    @Id
    private String id;
    private String title;
    private String company;
    private String location;
    private String description;
    private String imageUrl;
    private Date startDate;
    private Date endDate;
    private boolean isCurrent;

}
