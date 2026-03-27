package com.persequor.broker;

import com.persequor.Helper;
import com.persequor.model.Event;
import com.persequor.repository.StatisticsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class StatisticsListenerTest {
    @Mock
    private StatisticsRepository repository;
    @Mock
    private EventQueue eventQueue;

    private StatisticsListener statisticsListener;
    private Helper helper = new Helper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        statisticsListener = new StatisticsListener(eventQueue, repository);
    }

    @Test
    public void test10EventsOk() {
        testHandleEvents(10);
    }

    @Test
    public void testMoreThan10EventsOk() {
        testHandleEvents(12);
    }

    @Test
    public void testBulkEventsOk() {
        testHandleEvents(100);
    }

    @Test
    public void testBulkEventsOk1() {
        testHandleEvents(123);
    }


    public void testHandleEvents(int numberOfEvents) {
        for (int i = 0; i < numberOfEvents; i++) {
            statisticsListener.handle(helper.constructCreateEvent(), i);
        }
        // verify that update was called once

        verify(repository).updateStatistics(any(LocalDate.class), eq(numberOfEvents));

        // very all evenets pushed
        verify(eventQueue, times(numberOfEvents)).push(eq("statistics-queue"), any(Event.class));

        verify(eventQueue, times(numberOfEvents)).acknowledge(anyInt());

    }

    @Test
    public void testNoAcknowledgementError() {
        doThrow(new RuntimeException("Db error")).when(repository).updateStatistics(any(), anyInt());

        try {
            for (int i = 0; i < 10; i++) {
                statisticsListener.handle(helper.constructCreateEvent(), i);

            }
        } catch (RuntimeException ignored) {
        }

        // verify no acks happened because of error
        verify(eventQueue, never()).acknowledge(anyInt());
    }

    /// TODO: Implement tests as needed
}
