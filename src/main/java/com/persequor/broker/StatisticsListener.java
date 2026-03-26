package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.model.EventList;
import com.persequor.repository.StatisticsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsListener implements EventListener {
    private final EventQueue eventQueue;
    private final StatisticsRepository statisticsRepository;
    private static final int MAX_BATCH_EVENTS = 10;

    public StatisticsListener(EventQueue eventQueue, StatisticsRepository statisticsRepository) {
        this.eventQueue = eventQueue;
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public void handle(Event event, int deliveryTag) {
        int eventCounter = 0;
        // TODO: Collect up to 10 events in a batch before processing
        if (eventCounter == MAX_BATCH_EVENTS) {
            // TODO: call updateStatistics as few times as needed
            statisticsRepository.updateStatistics(LocalDate.now(), event.getTrackedItemIds().size());
        }
        eventQueue.push("statistics-queue", event);

        // TODO: acknowledge processed events
        eventQueue.acknowledge(deliveryTag);
    }
}
