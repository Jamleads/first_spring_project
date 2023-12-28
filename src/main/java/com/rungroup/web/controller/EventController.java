package com.rungroup.web.controller;

import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public  String createEventForm(@PathVariable("clubId") Long clubId, Model model ) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @GetMapping("/events")
    public  String eventList(Model model) {
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events", events);
        return "events-list";
    }

    @PostMapping("/events/{clubId}")
    public  String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event")EventDto eventDto, Model model) {
        eventService.createEvent(clubId, eventDto);
        return "redirect:/club/" + clubId;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") long eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }
}
