package com.gorevce.task_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String id;
    private String name;
    private String description;
    private String status;
    private Integer duration;
    private Integer wageCeiling;
    private Integer wageFloor;
    private String difficulty;
    private String companyId;
    private String freelancerId;
    private Integer applicationCount;
    private Integer commentCount;
}
