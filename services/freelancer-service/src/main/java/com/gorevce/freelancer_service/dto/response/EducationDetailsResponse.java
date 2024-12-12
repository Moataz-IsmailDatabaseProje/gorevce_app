package com.gorevce.freelancer_service.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationDetailsResponse {
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
