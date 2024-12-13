package com.gorevce.freelancer_service.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private String id;
    private String reviewerId;
    private String review;
    private Date createdAt;
    private int rating;
    private String imageUrl;
    private String freelancerId;
}
