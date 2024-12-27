package com.gorevce.task_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponse {
    private String id;
    private String taskId;
    private String status;
    private String comment;
    private Integer wage;
    private Integer duration;
    private String freelancerId;
    private String date;
}
