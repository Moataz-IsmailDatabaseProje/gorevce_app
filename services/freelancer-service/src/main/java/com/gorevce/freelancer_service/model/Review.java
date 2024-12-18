package com.gorevce.freelancer_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reviews")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    private String id;
    private String reviewerId;
    private String review;
    private int rating;
    private Date createdAt;
    private String imageUrl;
    private String freelancerId;
}
