package com.gorevce.freelancer_service.model;

import com.gorevce.freelancer_service.model.enums.PlatformEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document(collection = "social_media")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialMedia {
    @Id
    private String id;
    @Field(targetType = FieldType.STRING)
    private PlatformEnum platform;
    private String url;
    private String imageUrl;
    private String freelancerId;

}
