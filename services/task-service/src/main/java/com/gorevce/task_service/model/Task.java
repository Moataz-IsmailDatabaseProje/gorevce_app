package com.gorevce.task_service.model;

import com.gorevce.task_service.model.enums.DifficultyLevel;
import com.gorevce.task_service.model.enums.TaskStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private TaskStatus status;
    private Integer duration;
    private Integer wageCeiling;
    private Integer wageFloor;
    private DifficultyLevel difficulty;
    private String companyId;
    private List<String> applicationIds;
    private String freelancerId;
    private String addressId;
    private List<String> imageUrls;
    // todo: add address
    // todo: add imageUrls
}
