package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ReviewAddedEvent extends ApplicationEvent {

        private final String freelancerId;
        private final String reviewId;

        public ReviewAddedEvent(Object source, String freelancerId, String reviewId) {
            super(source);
            this.freelancerId = freelancerId;
            this.reviewId = reviewId;
        }
}
