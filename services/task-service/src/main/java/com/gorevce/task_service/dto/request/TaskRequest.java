package com.gorevce.task_service.dto.request;

import lombok.*;

import java.util.List;

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
    private String addressId;
    private List<String> imageUrls;
}
