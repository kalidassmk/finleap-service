package com.finleap.notification.repository.impl;

import com.finleap.notification.entity.Job;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.repository.JobRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * com.finleap.notification.repository
 *
 * @author Kalidass Mahalingam 13/2/2019
 */
@Component
public class JobRepoImpl implements JobRepo {

    private static final String JOB_BY_SCHEDULE_ID = "SELECT * FROM JOB WHERE SCHEDULE_ID = :scheduleId";

    private static final String UPDATE_JOB_QUEUE = "UPDATE JOB_QUEUE SET STATUS = ? WHERE id = ?";

    private static final  String CREATE_JOB_QUEUE = "INSERT INTO JOB_QUEUE (ID, JOB_NAME,JOB_TYPE,JOB_ID,STATUS,CREATED_ON) VALUES (?, ?, ?,?,?,?)";


    private final Logger logger = LoggerFactory.getLogger(JobRepoImpl.class);


    /**
     * The Named parameter jdbc template.
     */
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Instantiates a new Job repo.
     */
    public JobRepoImpl() {
    }

    /**
     * The Jdbc template.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Instantiates a new Job repo.
     *
     * @param namedParameterJdbcTemplate the named parameter jdbc template
     */
    public JobRepoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Job getJobByScheduleId(String scheduleId) {
        logger.info("Get the JobBy ScheduleId {} ", scheduleId);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("scheduleId", scheduleId);
        List<Job> job = namedParameterJdbcTemplate.query(JOB_BY_SCHEDULE_ID, namedParameters, new JobRowMapper());
        return job != null && !job.isEmpty() ? job.get(0) : null;
    }

    @Override
    public void createJobQueue(JobQueue jobQueue) {
        logger.info("Create Job Queue {} ", jobQueue.toString());
        int count = jdbcTemplate.update(CREATE_JOB_QUEUE, new Object[]{jobQueue.getId(),
                jobQueue.getJobName(), jobQueue.getJobType(), jobQueue.getJobId(), jobQueue.getStatus(), jobQueue.getCreatedOn()
        });
        logger.info("created {} ", count > 0 ? true : false);
    }


    @Override
    public JobQueue getJobQueue() {


        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", "NEW");
        List<JobQueue> jobQueuesList = namedParameterJdbcTemplate.query("SELECT * FROM job_queue where STATUS = :status", namedParameters, new JobQueueRowMapper());
        JobQueue jobQueues = jobQueuesList != null && !jobQueuesList.isEmpty() ? jobQueuesList.get(0) : null;
        if (jobQueues != null) {
            logger.info("Get the Job Queue {} ", jobQueues.toString());
            updateJobQueue("INPROGRESS", jobQueues.getId());
            logger.info("update the Job Queue status {} ", "INPROGRESS");

        }

        return jobQueues;
    }

    @Override
    public void updateJobQueue(String status, String jobQueueId) {
        // define query arguments
        Object[] params = {status, jobQueueId};
        // define SQL types of the arguments
        int[] types = {Types.VARCHAR, Types.BIGINT};
        int rows = jdbcTemplate.update(UPDATE_JOB_QUEUE, params, types);
        logger.info(rows + " row(s) updated.");

    }

    /**
     * The type Scheduler row mapper.
     */
    public static class JobRowMapper implements RowMapper {

        /**
         * The Id.
         */
        static final String ID = "ID";
        /**
         * The Job name.
         */
        static final String JOB_NAME = "JOB_NAME";
        /**
         * The Triggered by.
         */
        static final String TRIGGERED_BY = "TRIGGERED_BY";
        /**
         * The Job type.
         */
        static final String JOB_TYPE = "JOB_TYPE";
        /**
         * The Status.
         */
        static final String STATUS = "STATUS";

        /**
         * The Last run on.
         */
        static final String LAST_RUN_ON = "LAST_RUN_ON";
        /**
         * The Schedule id.
         */
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

        /**
         * The Id.
         */
        static final String ID = "ID";
        /**
         * The Job name.
         */
        static final String JOB_NAME = "JOB_NAME";
        /**
         * The Job type.
         */
        static final String JOB_TYPE = "JOB_TYPE";
        /**
         * The Job id.
         */
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
