package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.model.EventList;
import com.persequor.repository.EventRepository;
import com.persequor.repository.exceptions.EventRepositoryErrorException;

import java.awt.*;
import java.util.Collections;

public class EventStorageListener
	implements EventListener
{
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
		//TODO: Validate that the incoming event is not an earlier event than the ones existing in the database (repository)

		//      - As part of this, consider how you think the events should be sorted, and why.
		//      - Handle validation errors the way you believe it should work
		EventList events = repository.get(incomingEvent.getTrackedItemIds());

		//TODO: Store event
		repository.persist(incomingEvent);

		//TODO: Pass event on to "statistics" queue and to "subscription" queue
		//TODO: Acknowledge processed events
	}
}
