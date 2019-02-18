package com.finleap.notification.repository;

import java.util.List;

import com.finleap.notification.entity.Schedule;

public interface SchedulerRepo {
	Schedule getSchedulerById(String id);
	List<Schedule> synchingNewSchedule();
	boolean updateSchedule(Schedule schedule);
}
