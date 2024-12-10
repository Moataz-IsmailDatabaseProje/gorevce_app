package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    @Id
    private String id;
    private String name;
}
