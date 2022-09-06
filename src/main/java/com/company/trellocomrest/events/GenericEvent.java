package com.company.trellocomrest.events;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class GenericEvent<T> extends ApplicationEvent {
    private T what;
    protected boolean success;

    public GenericEvent(T what, boolean success) {
        super(what);
        this.what = what;
        this.success = success;
    }
}
