package com.github.alissonmartineli.eventmanager.repository;

import com.github.alissonmartineli.eventmanager.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
