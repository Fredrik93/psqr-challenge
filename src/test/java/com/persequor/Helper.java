package com.persequor;

import com.persequor.model.Event;
import com.persequor.model.EventAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Helper {

    // event 0
    public Event constructCreateEvent() {

        Event event = new Event();
        event.setEventTime(LocalDateTime.of(2025,5,3,9,5,5));
        UUID id = UUID.randomUUID();
        event.setId(id);
        event.setAction(EventAction.CREATE);
        event.setSource("source");
        event.setRecordTime(LocalDateTime.of(2025,5,1,8,5,5));
        List<String> trackedItemIds = new ArrayList<>(Arrays.asList("parcel1"));
        event.setTrackedItemIds(trackedItemIds);
        return event;
    }
    // Event 1
    public Event constructPackagevent() {

        Event event = new Event();
        event.setEventTime(LocalDateTime.of(2025,5,6,10,10));
        UUID id = UUID.randomUUID();
        event.setId(id);
        event.setAction(EventAction.PACKAGE);
        event.setSource("source");
        event.setRecordTime(LocalDateTime.of(2025,5,6,8,10));
        List<String> trackedItemIds = new ArrayList<>(Arrays.asList("parcel1"));
        event.setTrackedItemIds(trackedItemIds);
        return event;
    }
    // Event 2
    public Event constructSendEvent() {

        Event event = new Event();
        event.setEventTime(LocalDateTime.of(2025,5,7,10,10));
        UUID id = UUID.randomUUID();
        event.setId(id);
        event.setAction(EventAction.SEND);
        event.setSource("source");
        event.setRecordTime(LocalDateTime.of(2025,5,7,8,10));
        List<String> trackedItemIds = new ArrayList<>(Arrays.asList("parcel1"));
        event.setTrackedItemIds(trackedItemIds);
        return event;
    }
    // Event 2 but its time is very early
    public Event constructSendEarlyEvent() {

        Event event = new Event();
        // Something went wrong in creating the time so the year is now 2023, way too early
        event.setEventTime(LocalDateTime.of(2023,5,7,10,10));
        UUID id = UUID.randomUUID();
        event.setId(id);
        event.setAction(EventAction.SEND);
        event.setSource("source");
        event.setRecordTime(LocalDateTime.of(2025,5,1,8,10));
        List<String> trackedItemIds = new ArrayList<>(Arrays.asList("parcel1"));
        event.setTrackedItemIds(trackedItemIds);
        return event;
    }


}
