package com.finleap.notification.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.finleap.notification.entity.Schedule;
import com.finleap.notification.repository.SchedulerRepo;

@Repository("SchedulerRepo")
public class SchedulerRepoImpl implements SchedulerRepo {

	@Autowired
	JdbcTemplate jdbcTemplateObject;

	private static Map<String, Schedule> scheduleMap = new HashMap<>();
	private static List<Schedule> scheduleList = new ArrayList<>();

	@Override
	public Schedule getSchedulerById(String id) {
		return scheduleMap.get(id);
	}

	@Override
	public List<Schedule> synchingNewSchedule() {
		return jdbcTemplateObject.query("Select * from Scheduler",  new SchedulerRowMapper());
	}

	@Override
	public boolean updateSchedule(Schedule schedule) {
		scheduleMap.put(schedule.getId(), schedule);
		return false;
	}

	/**
	 * The type Scheduler row mapper.
	 */
	public static class SchedulerRowMapper implements RowMapper {

		static final String ID = "ID";
		static final String DELAY = "DELAY";
		static final String FREQUENCY_VALUE = "FREQUENCY_VALUE";
		static final String FREQUENCY = "FREQUENCY";
		static final String TIME = "TIME";

		static final String DESCRIPTION = "DESCRIPTION";
		static final String NAME = "NAME";
		static final String NEXT_RUN = "NEXT_RUN";
		static final String START_DATE = "START_DATE";
		static final String START_DAY = "START_DAY";

		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			Schedule schedule = new Schedule();
			schedule.setId(rs.getString(ID));
			schedule.setDelay(rs.getString(DELAY));
			schedule.setFrequencyValues(rs.getInt(FREQUENCY_VALUE));
			schedule.setFrequency(rs.getString(FREQUENCY));
			schedule.setTime(rs.getString(TIME));
			schedule.setDescription(rs.getString(DESCRIPTION));
			schedule.setName(rs.getString(NAME));
			schedule.setNextRun(rs.getString(NEXT_RUN));
			schedule.setStartDate(rs.getDate(START_DATE));
			schedule.setStartDay(rs.getString(START_DAY));
			return schedule;
		}
	}
}
