package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.model.EventList;
import com.persequor.repository.StatisticsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsListener implements EventListener {
    private final EventQueue eventQueue;
    private final StatisticsRepository statisticsRepository;
    private static final int MAX_BATCH_EVENTS = 10;
    private final List<Event> batch = new ArrayList<>();

    public StatisticsListener(EventQueue eventQueue, StatisticsRepository statisticsRepository) {
        this.eventQueue = eventQueue;
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    public void handle(Event event, int deliveryTag) {
        batch.add(event);
        int totalTrackedItems = 0;
        for (Event e: batch){
            totalTrackedItems += e.getTrackedItemIds().size();
        }

        // TODO: Collect up to 10 events in a batch before processing
        if (batch.size() == MAX_BATCH_EVENTS) {
            // TODO: call updateStatistics as few times as needed
            try {
                statisticsRepository.updateStatistics(LocalDate.now(), totalTrackedItems);
                // TODO: acknowledge processed events

                batch.forEach(e -> eventQueue.acknowledge(deliveryTag));
            } catch (RuntimeException e) {
                batch.clear();
                System.out.println(e.getMessage());
            }
            batch.clear();
        }
        eventQueue.push("statistics-queue", event);

    }
}
