package com.gorevce.freelancer_service.event.listener;

import com.gorevce.freelancer_service.event.added.*;
import com.gorevce.freelancer_service.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FreelancerEventListener {
    @Autowired
    private FreelancerRepository freelancerRepository;

    @EventListener
    public void handleCertificateAddedEvent(CertificateAddedEvent event) {
        // add certificate to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getCertificates().add(event.getCertificateId());
                    freelancerRepository.save(freelancer);
                });
    }

    @EventListener
    public void handleEducationAddedEvent(EducationAddedEvent event) {
        // add education to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getEducation().add(event.getEducationId());
                    freelancerRepository.save(freelancer);
                });
    }

    @EventListener
    public void handleProjectAddedEvent(ProjectAddedEvent event) {
        // add project to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getProjects().add(event.getProjectId());
                    freelancerRepository.save(freelancer);
                });
    }

    @EventListener
    public void handleReviewAddedEvent(ReviewAddedEvent event) {
        // add review to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getReviews().add(event.getReviewId());
                    freelancerRepository.save(freelancer);
                });
    }

    @EventListener
    public void handleSocialMediaAddedEvent(SocialMediaAddedEvent event) {
        // add social media to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getSocialMedia().add(event.getSocialMediaId());
                    freelancerRepository.save(freelancer);
                });
    }

    @EventListener
    public void handleWorkExperienceAddedEvent(WorkExperienceAddedEvent event) {
        // add work experience to freelancer
        freelancerRepository.findById(event.getFreelancerId())
                .ifPresent(freelancer -> {
                    freelancer.getWorkExperience().add(event.getWorkExperienceId());
                    freelancerRepository.save(freelancer);
                });
    }
}
