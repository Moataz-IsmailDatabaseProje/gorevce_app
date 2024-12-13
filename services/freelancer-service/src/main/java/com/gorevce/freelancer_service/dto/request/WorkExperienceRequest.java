package com.gorevce.freelancer_service.dto.request;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkExperienceRequest {
    private String title;
    private String company;
    private String city;
    private String description;
    private String imageUrl;
    private Date startDate;
    private Date endDate;
    private boolean isCurrent;
    private String freelancerId;
}
