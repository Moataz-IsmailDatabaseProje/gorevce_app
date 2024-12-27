package com.gorevce.task_service.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequest {
    private String taskId;
    private String comment;
    private Integer wage;
    private Integer duration;
    private String freelancerId;
    private String date;
}
