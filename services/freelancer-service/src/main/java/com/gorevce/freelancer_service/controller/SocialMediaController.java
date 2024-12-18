package com.gorevce.freelancer_service.controller;


import com.gorevce.freelancer_service.dto.request.SocialMediaRequest;
import com.gorevce.freelancer_service.exception.CustomException;
import com.gorevce.freelancer_service.service.SocialMediaService;
import com.gorevce.freelancer_service.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/freelancer/social-media")
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;

    // create social media
    @PostMapping("/create-social-media")
    public ResponseEntity<?> createSocialMedia(@RequestBody SocialMediaRequest socialMedia) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media created successfully")
                            .httpStatusCode(200)
                            .response(socialMediaService.createSocialMedia(socialMedia))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());

        }
    }

    // get social media by id
    @GetMapping("/get-social-media")
    public ResponseEntity<?> getSocialMediaById(@RequestParam String id) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media retrieved successfully")
                            .httpStatusCode(200)
                            .response(socialMediaService.getSocialMediaById(id))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }

    // get social media by freelancer id
    @GetMapping("/get-social-media-by-freelancer")
    public ResponseEntity<?> getSocialMediaByFreelancerId(@RequestParam String freelancerId) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media retrieved successfully")
                            .httpStatusCode(200)
                            .response(socialMediaService.getSocialMediaByFreelancerId(freelancerId))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }

    // update social media
    @PutMapping("/update-social-media")
    public ResponseEntity<?> updateSocialMedia(@RequestParam String id, @RequestBody SocialMediaRequest socialMedia) {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media updated successfully")
                            .httpStatusCode(200)
                            .response(socialMediaService.updateSocialMedia(id, socialMedia))
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }

    // delete social media
    @DeleteMapping("/delete-social-media")
    public ResponseEntity<?> deleteSocialMedia(@RequestParam String id) {
        try {
            socialMediaService.deleteSocialMedia(id);
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media deleted successfully")
                            .httpStatusCode(200)
                            .response(null)
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }

    // get all social media
    @GetMapping("/get-all-social-media")
    public ResponseEntity<?> getAllSocialMedia() {
        try {
            return ResponseEntity.ok().body(
                    ApiResponse.builder()
                            .message("Social media retrieved successfully")
                            .httpStatusCode(200)
                            .response(socialMediaService.getAllSocialMedia())
                            .build()
            );
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatusCode()).body(
                    ApiResponse.builder()
                            .message(e.getMessage())
                            .httpStatusCode(e.getHttpStatusCode())
                            .response(e.getDetails())
                            .build());
        }
    }


}