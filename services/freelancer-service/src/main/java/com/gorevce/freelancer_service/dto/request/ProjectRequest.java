package com.gorevce.freelancer_service.dto.request;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
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
