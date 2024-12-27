package com.gorevce.task_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {
    private String name;
    private String description;
    private Integer duration;
    private Integer wageCeiling;
    private Integer wageFloor;
    private String difficulty;
    private String companyId;
}
