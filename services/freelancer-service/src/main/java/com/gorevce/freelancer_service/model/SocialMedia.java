package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "social_media")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialMedia {
    @Id
    private String id;
    private String name;
    private String url;
    private String imageUrl;

}
