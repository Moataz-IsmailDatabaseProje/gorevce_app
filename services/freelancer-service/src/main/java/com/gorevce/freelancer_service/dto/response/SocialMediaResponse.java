package com.gorevce.freelancer_service.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialMediaResponse {
    private String id;
    private String platform;
    private String url;
    private String imageUrl;
    private String freelancerId;
}
