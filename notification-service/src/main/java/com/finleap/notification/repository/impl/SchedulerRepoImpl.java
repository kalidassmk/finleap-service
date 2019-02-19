package com.finleap.notification.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.finleap.notification.entity.Schedule;
import com.finleap.notification.repository.SchedulerRepo;

/**
 * The type Scheduler repo.
 */
@Repository("SchedulerRepo")
public class SchedulerRepoImpl implements SchedulerRepo {

	/**
	 * The Jdbc template object.
	 */
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	private static Map<String, Schedule> scheduleMap = new HashMap<>();
	private static List<Schedule> scheduleList = new ArrayList<>();

	private final Logger logger = LoggerFactory.getLogger(JobRepoImpl.class);


	@Override
	public Schedule getSchedulerById(String id) {
		return scheduleMap.get(id);
	}

	@Override
	public List<Schedule> synchingNewSchedule() {
		logger.info("Synching New Schedule...................");
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

		/**
		 * The Id.
		 */
		static final String ID = "ID";
		/**
		 * The Delay.
		 */
		static final String DELAY = "DELAY";
		/**
		 * The Frequency value.
		 */
		static final String FREQUENCY_VALUE = "FREQUENCY_VALUE";
		/**
		 * The Frequency.
		 */
		static final String FREQUENCY = "FREQUENCY";
		/**
		 * The Time.
		 */
		static final String TIME = "TIME";

		/**
		 * The Description.
		 */
		static final String DESCRIPTION = "DESCRIPTION";
		/**
		 * The Name.
		 */
		static final String NAME = "NAME";
		/**
		 * The Next run.
		 */
		static final String NEXT_RUN = "NEXT_RUN";
		/**
		 * The Start date.
		 */
		static final String START_DATE = "START_DATE";
		/**
		 * The Start day.
		 */
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
