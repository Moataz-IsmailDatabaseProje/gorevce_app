package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ProjectAddedEvent extends ApplicationEvent {

        private final String freelancerId;
        private final String projectId;

        public ProjectAddedEvent(Object source, String freelancerId, String projectId) {
            super(source);
            this.freelancerId = freelancerId;
            this.projectId = projectId;
        }

}
