package com.finleap.notification.container;

import akka.actor.Cancellable;
import com.finleap.notification.entity.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Scheduler container.
 */
public class SchedulerContainer {

	@Autowired
	private ApplicationContext applicationContext;


	private Map<String, Schedule> schedulerContainer = new HashMap<>();
	private Map<String, Cancellable> cancellableSchedule = new HashMap<>();

	/**
	 * Synching existing scheduler list.
	 *
	 * @return the list
	 */
	public List<Schedule> synchingExistingScheduler() {
		return schedulerContainer.entrySet().stream().map(map -> map.getValue()).collect(Collectors.toList());
	}

	/**
	 * Get schedule.
	 *
	 * @param id the id
	 * @return the schedule
	 */
	public Schedule get(String id) {
		return schedulerContainer.get(id);
	}

	/**
	 * Gets cancellable schedule.
	 *
	 * @param id the id
	 * @return the cancellable schedule
	 */
	public Cancellable getCancellableSchedule(String id) {
		return cancellableSchedule.get(id);
	}

	/**
	 * Save schedule.
	 *
	 * @param schedule the schedule
	 * @return the schedule
	 */
	public Schedule save(Schedule schedule) {
		return schedulerContainer.put(schedule.getId(), schedule);
	}

	/**
	 * Remove schedule.
	 *
	 * @param id the id
	 * @return the schedule
	 */
	public Schedule remove(String id) {
		return schedulerContainer.remove(id);
	}

	/**
	 * Remove cancellable schedule cancellable.
	 *
	 * @param id the id
	 * @return the cancellable
	 */
	public Cancellable removeCancellableSchedule(String id) {
		return cancellableSchedule.remove(id);
	}

	/**
	 * Save cancellable schedule cancellable.
	 *
	 * @param id          the id
	 * @param cancellable the cancellable
	 * @return the cancellable
	 */
	public Cancellable saveCancellableSchedule(String id, Cancellable cancellable) {
		return cancellableSchedule.put(id, cancellable);
	}

	/**
	 * The type Instance helper.
	 */
	public static class InstanceHelper {
		private static SchedulerContainer schedulerContainer = new SchedulerContainer();
	}

	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static SchedulerContainer getInstance() {
		return InstanceHelper.schedulerContainer;
	}


}
