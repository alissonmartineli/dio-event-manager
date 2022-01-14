package com.github.alissonmartineli.eventmanager.service;

import java.util.List;

import com.github.alissonmartineli.eventmanager.dto.mapper.EventMapper;
import com.github.alissonmartineli.eventmanager.dto.request.EventDTO;
import com.github.alissonmartineli.eventmanager.dto.response.MessageResponseDTO;
import com.github.alissonmartineli.eventmanager.entity.Event;
import com.github.alissonmartineli.eventmanager.exception.EventNotFoundException;
import com.github.alissonmartineli.eventmanager.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EventService {

    private final EventRepository eventRepository;

    private static final EventMapper eventMapper = EventMapper.INSTANCE;

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();

        return events.stream()
                .map(eventMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    public EventDTO findById(Long id) throws EventNotFoundException {
        Event event = verifyIfExists(id);

        return eventMapper.toDTO(event);
    }

    public MessageResponseDTO create(EventDTO eventDTO) {
        Event event = eventMapper.toModel(eventDTO);
        Event savedEvent = eventRepository.save(event);

        return createMessageResponse("Event successfully created with ID ", savedEvent.getId());
    }

    public MessageResponseDTO update(Long id, EventDTO eventDTO) throws EventNotFoundException {
        verifyIfExists(id);

        Event event = eventMapper.toModel(eventDTO);
        Event updatedEvent = eventRepository.save(event);

        return createMessageResponse("Event successfully updated with ID ", updatedEvent.getId());
    }

    public void delete(Long id) throws EventNotFoundException {
        verifyIfExists(id);

        eventRepository.deleteById(id);
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }

    private Event verifyIfExists(Long id) throws EventNotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }
}
