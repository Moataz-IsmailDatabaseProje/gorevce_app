package com.gorevce.freelancer_service.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceResponse {
    private String id;
    private String title;
    private String company;
    private String imageUrl;
    private Date endDate;
    private boolean isCurrent;
    private String freelancerId;
}
