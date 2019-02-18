package com.finleap.notification.repository.impl;

import com.finleap.notification.entity.Job;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * com.finleap.notification.repository
 *
 * @author Kalidass Mahalingam
 * 13/2/2019
 */
@Component
public class JobRepoImpl implements JobRepo {

    private static final String JOB_BY_SCHEDULE_ID = "SELECT * FROM JOB WHERE SCHEDULE_ID = :scheduleId";

    /**
     * The Named parameter jdbc template.
     */
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JobRepoImpl() {
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JobRepoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Job getJobByScheduleId(String scheduleId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("scheduleId", scheduleId);
        List<Job> job = namedParameterJdbcTemplate.query(JOB_BY_SCHEDULE_ID, namedParameters, new JobRowMapper());
        return job != null && !job.isEmpty() ? job.get(0) : null;
    }

    @Override
    public void createJobQueue(JobQueue jobQueue) {
        String sql = "INSERT INTO JOB_QUEUE (ID, JOB_NAME,JOB_TYPE,JOB_ID,STATUS,CREATED_ON) VALUES (?, ?, ?,?,?,?)";
        int count = jdbcTemplate.update(sql, new Object[]{jobQueue.getId(),
                jobQueue.getJobName(), jobQueue.getJobType(), jobQueue.getJobId(),jobQueue.getStatus(),jobQueue.getCreatedOn()
        });

        System.out.println("count = " + count);
    }


    @Override
    public JobQueue getJobQueue() {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", "NEW");
        List<JobQueue> jobQueues = namedParameterJdbcTemplate.query("SELECT * FROM job_queue where STATUS = :status", namedParameters, new JobQueueRowMapper());
        return jobQueues != null && !jobQueues.isEmpty() ? jobQueues.get(0) : null;
    }

    /**
     * The type Scheduler row mapper.
     */
    public static class JobRowMapper implements RowMapper {

        static final String ID = "ID";
        static final String JOB_NAME = "JOB_NAME";
        static final String TRIGGERED_BY = "TRIGGERED_BY";
        static final String JOB_TYPE = "JOB_TYPE";
        static final String STATUS = "STATUS";

        static final String LAST_RUN_ON = "LAST_RUN_ON";
        static final String SCHEDULE_ID = "SCHEDULE_ID";

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Job job = new Job();
            job.setId(rs.getString(ID));
            job.setJobName(rs.getString(JOB_NAME));
            job.setJobType(rs.getString(TRIGGERED_BY));
            job.setLastRunOn(rs.getString(JOB_TYPE));
            job.setScheduleId(rs.getString(STATUS));
            job.setStatus(rs.getString(LAST_RUN_ON));
            job.setTriggeredBy(rs.getString(SCHEDULE_ID));
            return job;
        }
    }


    /**
     * The type Scheduler row mapper.
     */
    public static class JobQueueRowMapper implements RowMapper {

        static final String ID = "ID";
        static final String JOB_NAME = "JOB_NAME";
        static final String JOB_TYPE = "JOB_TYPE";
        static final String JOB_ID = "JOB_ID";

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            JobQueue jobQueue = new JobQueue();
            jobQueue.setId(rs.getString(ID));
            jobQueue.setJobName(rs.getString(JOB_NAME));
            jobQueue.setJobType(rs.getString(JOB_TYPE));
            jobQueue.setJobId(rs.getString(JOB_ID));
            return jobQueue;
        }
    }
}
