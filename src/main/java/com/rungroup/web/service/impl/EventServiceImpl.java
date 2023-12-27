package com.rungroup.web.service.impl;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.Event;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.repository.EventRepository;
import com.rungroup.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.rungroup.web.mapper.EventMapper.mapToEvent;

@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
         Club club = clubRepository.findById(clubId).get();
         Event event = mapToEvent(eventDto);
         event.setClub(club);
         eventRepository.save(event);
    }
 
}
