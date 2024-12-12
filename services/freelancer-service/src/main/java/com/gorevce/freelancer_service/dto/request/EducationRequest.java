package com.gorevce.freelancer_service.dto.request;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequest {
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
