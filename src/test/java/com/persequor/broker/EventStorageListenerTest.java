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
import static org.mockito.Mockito.when;

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
        EventStorageListener l = new EventStorageListener(repository, eventQueue);
        String myItemId = "parcel1";
        //get all ids, whih should be only one, so one item with several events
        List<String> ids = incomingEvent.getTrackedItemIds();

        // assert that my parcel id exist
        assertTrue(ids.contains(myItemId));

        EventList listOfEventsForTheParcel = repository.get(ids);
        // get the first one
        Event e1 = listOfEventsForTheParcel.get(0);


    }
    /// TODO: Implement tests as needed



}


