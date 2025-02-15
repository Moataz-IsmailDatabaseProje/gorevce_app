package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "education")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {
    @Id
    private String id;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private Boolean isCurrentlyStudying;
    private String grade;
    private String description;
    private String imageUrl;
    private String freelancerId;

}
