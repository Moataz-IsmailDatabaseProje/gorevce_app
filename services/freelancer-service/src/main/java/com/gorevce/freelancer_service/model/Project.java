package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "projects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String projectUrl;
    private String projectType;
    private String projectStatus;
    private Date projectStartDate;
    private Date projectEndDate;

}
