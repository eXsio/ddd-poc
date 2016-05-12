package com.ddd.poc.query.core.controller;

import com.ddd.poc.domain.api.RestUrls;
import com.ddd.poc.domain.core.dao.EventEntityDao;
import com.ddd.poc.domain.core.dto.CommandDTO;
import com.ddd.poc.domain.core.dto.EventDTO;
import com.ddd.poc.domain.core.model.CommandEntity;
import com.ddd.poc.domain.core.model.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(RestUrls.EVENTS)
public class EventsQueryController {

    private final EventEntityDao eventEntityDao;

    @Autowired
    public EventsQueryController(EventEntityDao eventEntityDao) {
        this.eventEntityDao = eventEntityDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<EventDTO> getEvents() {
        return eventEntityDao.findAllOrderByCreatedAt().stream().map(this::getEventDTO).collect(Collectors.toList());
    }

    protected EventDTO getEventDTO(EventEntity entity) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(entity.getId());
        eventDTO.setCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(entity.getCreatedAt().getTime()), ZoneId.systemDefault()));
        eventDTO.setEventClass(entity.getEventClass());
        eventDTO.setData(entity.getEventData());
        eventDTO.setUuid(entity.getUuid());
        CommandEntity commandEntity = entity.getCommand();
        eventDTO.setCommand(new CommandDTO() {
            {
                setCommandClass(commandEntity.getCommandClass());
                setData(commandEntity.getCommandData());
                setCreatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(commandEntity.getCreatedAt().getTime()), ZoneId.systemDefault()));
                setId(commandEntity.getId());
                setUuid(commandEntity.getUuid());

            }
        });
        return eventDTO;
    }
}
