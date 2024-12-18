package com.gorevce.freelancer_service.dto.response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDetailsResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String projectUrl;
    private String projectType;
    private String projectStatus;
    private Date projectStartDate;
    private Date projectEndDate;
    private String freelancerId;
}
