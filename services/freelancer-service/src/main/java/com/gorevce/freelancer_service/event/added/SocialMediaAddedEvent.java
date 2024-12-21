package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SocialMediaAddedEvent extends ApplicationEvent {

        private final String freelancerId;
        private final String socialMediaId;

        public SocialMediaAddedEvent(Object source, String freelancerId, String socialMediaId) {
            super(source);
            this.freelancerId = freelancerId;
            this.socialMediaId = socialMediaId;
        }
}
