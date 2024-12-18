package com.gorevce.freelancer_service.service;

import com.gorevce.freelancer_service.dto.request.SocialMediaRequest;
import com.gorevce.freelancer_service.dto.response.SocialMediaResponse;
import com.gorevce.freelancer_service.model.SocialMedia;

import java.util.List;

public interface SocialMediaService {

    // create social media
    SocialMediaResponse createSocialMedia(SocialMediaRequest socialMedia);

    // get social media by id
    SocialMediaResponse getSocialMediaById(String id);

    // get social media by freelancer id
    List<SocialMediaResponse> getSocialMediaByFreelancerId(String freelancerId);

    // update social media
    SocialMediaResponse updateSocialMedia(String id, SocialMediaRequest socialMedia);

    // delete social media
    void deleteSocialMedia(String id);

    // get all social media
    List<SocialMediaResponse> getAllSocialMedia();


    SocialMedia getSocialMediaModelById(String id);
}
