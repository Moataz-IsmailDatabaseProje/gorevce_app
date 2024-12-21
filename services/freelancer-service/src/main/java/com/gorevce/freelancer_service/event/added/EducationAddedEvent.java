package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class EducationAddedEvent extends ApplicationEvent {

        private final String freelancerId;
        private final String educationId;

        public EducationAddedEvent(Object source, String freelancerId, String educationId) {
            super(source);
            this.freelancerId = freelancerId;
            this.educationId = educationId;
        }
}
