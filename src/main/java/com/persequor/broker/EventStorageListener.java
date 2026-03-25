package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.model.EventList;
import com.persequor.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DateTimeException;

public class EventStorageListener
	implements EventListener
{
	private static final Logger log = LoggerFactory.getLogger(EventStorageListener.class);
	private final EventRepository repository;
	private EventQueue eventQueue;

	public EventStorageListener(
			EventRepository repository
			, EventQueue eventQueue
	) {
		this.repository = repository;
		this.eventQueue = eventQueue;
	}

	@Override
	public void handle(Event incomingEvent, int deliveryTag) {
		log.info("Received event: {} with delivery tag: {}", incomingEvent.getId(), deliveryTag);

		EventList events = repository.get(incomingEvent.getTrackedItemIds());
		for (Event event : events) {
			if (!event.getEventTime().isBefore(incomingEvent.getEventTime())) {
				log.warn("Rejecting event, event is earlier than existing event time");
			}
		}

		log.debug("Storing event: {}", incomingEvent.getId());
		//TODO: Store event
		repository.persist(incomingEvent);

		//TODO: Pass event on to "statistics" queue and to "subscription" queue
		//TODO: Acknowledge processed events
	}
}
