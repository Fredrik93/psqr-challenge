package com.persequor.broker;

import com.persequor.model.Event;
import com.persequor.repository.exceptions.EventRepositoryErrorException;

public interface EventListener {
	void handle(Event event, int deliveryTag);
}
