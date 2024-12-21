package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class WorkExperienceAddedEvent extends ApplicationEvent {

            private final String freelancerId;
            private final String workExperienceId;

            public WorkExperienceAddedEvent(Object source, String freelancerId, String workExperienceId) {
                super(source);
                this.freelancerId = freelancerId;
                this.workExperienceId = workExperienceId;
            }

}
