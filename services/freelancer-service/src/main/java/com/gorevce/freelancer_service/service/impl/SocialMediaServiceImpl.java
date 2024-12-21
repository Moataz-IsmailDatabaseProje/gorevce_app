package com.gorevce.freelancer_service.service.impl;


import com.gorevce.freelancer_service.dto.request.SocialMediaRequest;
import com.gorevce.freelancer_service.dto.response.SocialMediaResponse;
import com.gorevce.freelancer_service.event.added.SocialMediaAddedEvent;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.model.SocialMedia;
import com.gorevce.freelancer_service.model.enums.PlatformEnum;
import com.gorevce.freelancer_service.repository.SocialMediaRepository;
import com.gorevce.freelancer_service.service.SocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {


    @Autowired
    private SocialMediaRepository socialMediaRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public SocialMediaResponse createSocialMedia(SocialMediaRequest socialMedia) {
        // check if social media enum is valid
        if (!PlatformEnum.isPlatformExist(socialMedia.getPlatform())) {
            throw new CustomException(
                    "Invalid platform",
                    400,
                    Map.of(
                            "platform", socialMedia.getPlatform()
                    )
            );
        }
        // check if freelancer id is null
        if (socialMedia.getFreelancerId() == null) {
            throw new CustomException(
                    "Freelancer id is required",
                    400,
                    Map.of(
                            "freelancerId", "null"
                    )
            );
        }
        // create social media
        SocialMedia socialMediaModel = SocialMedia.builder()
                .platform(PlatformEnum.valueOf(socialMedia.getPlatform().toUpperCase()))
                .url(socialMedia.getUrl())
                .imageUrl(socialMedia.getImageUrl())
                .freelancerId(socialMedia.getFreelancerId())
                .build();
        // save social media to database
        SocialMedia saved = socialMediaRepository.save(socialMediaModel);
        eventPublisher.publishEvent(new SocialMediaAddedEvent(this, saved.getFreelancerId(), saved.getId()));
        // return social media
        return SocialMediaResponse.builder()
                .id(saved.getId())
                .platform(saved.getPlatform().name())
                .url(saved.getUrl())
                .imageUrl(saved.getImageUrl())
                .freelancerId(saved.getFreelancerId())
                .build();
    }

    @Override
    public SocialMediaResponse getSocialMediaById(String id) {
        // get social media by id
        SocialMedia socialMedia = socialMediaRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Social media not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // return social media
        return SocialMediaResponse.builder()
                .id(socialMedia.getId())
                .platform(socialMedia.getPlatform().name())
                .url(socialMedia.getUrl())
                .imageUrl(socialMedia.getImageUrl())
                .freelancerId(socialMedia.getFreelancerId())
                .build();
    }

    @Override
    public List<SocialMediaResponse> getSocialMediaByFreelancerId(String freelancerId) {
        // get social media by freelancer id
        List<SocialMedia> socialMediaList = socialMediaRepository.findByFreelancerId(freelancerId);
        // return social media
        return socialMediaList.stream()
                .map(socialMedia -> SocialMediaResponse.builder()
                        .id(socialMedia.getId())
                        .platform(socialMedia.getPlatform().name())
                        .url(socialMedia.getUrl())
                        .imageUrl(socialMedia.getImageUrl())
                        .freelancerId(socialMedia.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public SocialMediaResponse updateSocialMedia(String id, SocialMediaRequest socialMedia) {
        // get social media by id
        SocialMedia socialMediaModel = socialMediaRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException("Social media not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
        // check if social media enum is valid
        if (!PlatformEnum.isPlatformExist(socialMedia.getPlatform().toUpperCase())) {
            throw new CustomException(
                    "Invalid platform",
                    400,
                    Map.of(
                            "platform", socialMedia.getPlatform()
                    )
            );
        }

        // update social media
        socialMediaModel.setPlatform(PlatformEnum.valueOf(socialMedia.getPlatform()));
        socialMediaModel.setUrl(socialMedia.getUrl());
        socialMediaModel.setImageUrl(socialMedia.getImageUrl());
        socialMediaModel.setFreelancerId(socialMedia.getFreelancerId());
        // save social media to database
        SocialMedia saved = socialMediaRepository.save(socialMediaModel);
        // return social media
        return SocialMediaResponse.builder()
                .id(saved.getId())
                .platform(saved.getPlatform().name())
                .url(saved.getUrl())
                .imageUrl(saved.getImageUrl())
                .freelancerId(saved.getFreelancerId())
                .build();
    }

    @Override
    public void deleteSocialMedia(String id) {
        // check if social media exists
        if (!socialMediaRepository.existsById(id)) {
            throw new CustomException(
                    "Social media not found",
                    404,
                    Map.of(
                            "id", id
                    )
            );
        }
        // delete social media
        socialMediaRepository.deleteById(id);

    }

    @Override
    public List<SocialMediaResponse> getAllSocialMedia() {
        // get all social media
        List<SocialMedia> socialMediaList = socialMediaRepository.findAll();
        // return social media
        return socialMediaList.stream()
                .map(socialMedia -> SocialMediaResponse.builder()
                        .id(socialMedia.getId())
                        .platform(socialMedia.getPlatform().name())
                        .url(socialMedia.getUrl())
                        .imageUrl(socialMedia.getImageUrl())
                        .freelancerId(socialMedia.getFreelancerId())
                        .build())
                .toList();
    }

    @Override
    public SocialMedia getSocialMediaModelById(String id) {
        // get social media by id
        return socialMediaRepository.findById(id)
                .orElseThrow(
                        () -> new CustomException(
                                "Social media not found",
                                404,
                                Map.of(
                                        "id", id
                                )
                        )
                );
    }
}
