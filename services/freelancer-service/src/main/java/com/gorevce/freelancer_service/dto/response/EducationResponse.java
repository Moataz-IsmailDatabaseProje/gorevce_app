package com.gorevce.freelancer_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationResponse {
    private String id;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private String startYear;
    private String endYear;
    private Boolean isCurrentlyStudying;
    private String freelancerId;
}
