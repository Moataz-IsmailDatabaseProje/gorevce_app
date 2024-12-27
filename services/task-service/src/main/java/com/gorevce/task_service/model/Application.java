package com.gorevce.task_service.model;


import com.gorevce.task_service.model.enums.ApplicationStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "applications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    @Id
    private String id;
    private String taskId;
    private ApplicationStatus status;
    private String comment;
    private Integer wage;
    private Integer duration;
    private String freelancerId;
    private Date date;
}
