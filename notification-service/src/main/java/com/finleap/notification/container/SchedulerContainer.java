package com.finleap.notification.container;

import akka.actor.Cancellable;
import com.finleap.notification.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SchedulerContainer {

	@Autowired
	private ApplicationContext applicationContext;


	private Map<String, Schedule> schedulerContainer = new HashMap<>();
	private Map<String, Cancellable> cancellableSchedule = new HashMap<>();

	public List<Schedule> synchingExistingScheduler() {
		return schedulerContainer.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
	}

	public Schedule get(String id) {
		return schedulerContainer.get(id);
	}

	public Cancellable getCancellableSchedule(String id) {
		return cancellableSchedule.get(id);
	}

	public Schedule save(Schedule schedule) {
		return schedulerContainer.put(schedule.getId(), schedule);
	}

	public Schedule remove(String id) {
		return schedulerContainer.remove(id);
	}

	public Cancellable removeCancellableSchedule(String id) {
		return cancellableSchedule.remove(id);
	}

	public Cancellable saveCancellableSchedule(String id, Cancellable cancellable) {
		return cancellableSchedule.put(id, cancellable);
	}

	public static class InstanceHelper {
		private static SchedulerContainer schedulerContainer = new SchedulerContainer();
	}

	public static SchedulerContainer getInstance() {
		return InstanceHelper.schedulerContainer;
	}


}
