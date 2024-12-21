package com.gorevce.freelancer_service.event.added;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CertificateAddedEvent extends ApplicationEvent {

    private final String freelancerId;
    private final String certificateId;

    public CertificateAddedEvent(Object source, String freelancerId, String certificateId) {
        super(source);
        this.freelancerId = freelancerId;
        this.certificateId = certificateId;
    }


}
