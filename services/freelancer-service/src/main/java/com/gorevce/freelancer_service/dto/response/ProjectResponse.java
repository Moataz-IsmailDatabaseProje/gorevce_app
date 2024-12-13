package com.gorevce.freelancer_service.dto.response;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String projectUrl;
    private String freelancerId;
}
