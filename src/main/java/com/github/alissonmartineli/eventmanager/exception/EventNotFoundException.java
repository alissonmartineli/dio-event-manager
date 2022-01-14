package com.github.alissonmartineli.eventmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends Exception {

    public EventNotFoundException(Long id) {
        super(String.format("Event with ID %s not found!", id));
    }
}
