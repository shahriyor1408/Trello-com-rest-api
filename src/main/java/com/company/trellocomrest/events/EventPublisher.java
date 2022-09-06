package com.company.trellocomrest.events;

import com.company.trellocomrest.events.project.ProjectColumnMoveEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T> void publishCustomEvent(GenericEvent<ProjectColumnMoveEvent> event) {
        System.out.println("Publishing custom event. ");
        if (event.success) {
            applicationEventPublisher.publishEvent(event.getWhat());
        }
    }
}
