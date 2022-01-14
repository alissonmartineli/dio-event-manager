package com.github.alissonmartineli.eventmanager.dto.mapper;

import com.github.alissonmartineli.eventmanager.dto.request.EventDTO;
import com.github.alissonmartineli.eventmanager.entity.Event;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    Event toModel(EventDTO eventDTO);

    EventDTO toDTO(Event dto);
}
