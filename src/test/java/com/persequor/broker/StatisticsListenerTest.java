package com.persequor.broker;

import com.persequor.Helper;
import com.persequor.model.Event;
import com.persequor.repository.StatisticsRepository;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

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

	void testOk(){
		Event event = helper.constructCreateEvent();
		statisticsListener.handle(event,41);
	}

	/// TODO: Implement tests as needed
}
