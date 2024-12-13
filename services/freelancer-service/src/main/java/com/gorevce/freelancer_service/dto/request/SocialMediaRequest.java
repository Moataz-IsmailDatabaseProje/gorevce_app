package com.gorevce.freelancer_service.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialMediaRequest {
    private String platform;
    private String url;
    private String imageUrl;
    private String freelancerId;
}
