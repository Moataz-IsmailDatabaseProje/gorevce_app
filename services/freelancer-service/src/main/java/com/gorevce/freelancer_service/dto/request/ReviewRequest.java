package com.gorevce.freelancer_service.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewRequest {
    private String reviewerId;
    private String review;
    private int rating;
    private String imageUrl;
    private String freelancerId;
}
