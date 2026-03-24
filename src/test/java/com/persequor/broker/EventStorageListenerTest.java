package com.persequor.broker;

import com.persequor.Helper;
import com.persequor.model.Event;
import com.persequor.model.EventAction;
import com.persequor.model.EventList;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EventStorageListenerTest {
    @Mock
    private EventRepository repository;
    @Mock
    private EventQueue eventQueue;

    private EventStorageListener listener;

    Event incomingEvent = new Event();

    private Helper helper = new Helper();

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        EventList eventList = new EventList();
        eventList.add(helper.constructCreateEvent());
        eventList.add(helper.constructPackagevent());
        when(repository.get(any())).thenReturn(eventList);
        // Set up incoming event
        incomingEvent = helper.constructSendEvent();

        listener = new EventStorageListener(repository, eventQueue);
    }
    @Test
    public void testOk() {

        // create earliy incoming event
        Event incomingEvent;
        incomingEvent = helper.constructSendEvent();
        listener.handle(incomingEvent,41);

        // verify that it was stored
        verify(repository).persist(incomingEvent);
    }
   @Test
    public void incomingEventIsTooEarly(){

        // create earliy incoming event
       Event incomingEventThatIsTooEarly = new Event();
       incomingEventThatIsTooEarly = helper.constructSendEarlyEvent();
       listener.handle(incomingEventThatIsTooEarly,42);

       // verify that it was not stored because it is faulty
       verify(repository, never()).persist(incomingEventThatIsTooEarly);
   }



}


