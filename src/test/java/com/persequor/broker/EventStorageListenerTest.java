package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.model.EventAction;
import com.persequor.repository.EventRepository;
import com.persequor.repository.exceptions.EventRepositoryErrorException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;

public class EventStorageListenerTest {
    @Mock
    private EventRepository repository;
    @Mock
    private EventQueue eventQueue;

    private EventStorageListener listener;

    Event incomingEvent = new Event();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        incomingEvent = createEvent();
        listener = new EventStorageListener(repository, eventQueue);
    }

    Event createEvent() {

        Event incomingEvent = new Event();
        incomingEvent.setEventTime(LocalDateTime.now());
        UUID id = new UUID(1, 1);
        incomingEvent.setId(id);
        incomingEvent.setAction(EventAction.CREATE);
        incomingEvent.setSource("source");
        incomingEvent.setRecordTime(LocalDateTime.now());
        List<String> trackedItemIds = new ArrayList<>(Arrays.asList("t1", "t2", "t3"));
        incomingEvent.setTrackedItemIds(trackedItemIds);
        return incomingEvent;
    }

    @Test
    public void testOk() throws EventRepositoryErrorException {

        listener.handle(incomingEvent, 1);
    }
    /// TODO: Implement tests as needed
}
