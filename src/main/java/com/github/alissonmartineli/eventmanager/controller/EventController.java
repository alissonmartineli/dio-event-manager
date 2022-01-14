package com.github.alissonmartineli.eventmanager.controller;

import java.util.List;

import com.github.alissonmartineli.eventmanager.dto.request.EventDTO;
import com.github.alissonmartineli.eventmanager.dto.response.MessageResponseDTO;
import com.github.alissonmartineli.eventmanager.exception.EventNotFoundException;
import com.github.alissonmartineli.eventmanager.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/events")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDTO> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO findById(@PathVariable(value = "id") Long id) throws EventNotFoundException {
        return eventService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO create(@Validated @RequestBody EventDTO event) {
        return eventService.create(event);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO update(@PathVariable(value = "id") Long id, @RequestBody EventDTO event)
            throws EventNotFoundException {
        event.setId(id);
        return eventService.update(id, event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) throws EventNotFoundException {
        eventService.delete(id);
    }
}
