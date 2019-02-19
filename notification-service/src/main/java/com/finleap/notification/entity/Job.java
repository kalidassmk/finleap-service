package com.finleap.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Job.
 */
@Entity
@Table(name = "Job")
public class Job {

    /**
     * The constant JOB_STATUS_COMPLETED.
     */
    public static final String JOB_STATUS_COMPLETED = "COMPLETED";
    /**
     * The constant JOB_TRIGGERED_BY_SCHEDULER.
     */
    public static final String JOB_TRIGGERED_BY_SCHEDULER = "BY_SCHEDULER";
    /**
     * The constant JOB_TRIGGERED_ON_DEMAND.
     */
    public static final String JOB_TRIGGERED_ON_DEMAND = "ON_DEMAND";

	@Id
	@Column(name = "ID")
	private String id ;

	@Column(name = "JOB_NAME")
	private String jobName ;

	@Column(name = "TRIGGERED_BY" )
	private String triggeredBy;

	@Column(name = "JOB_TYPE")
	private String jobType;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "LAST_RUN_ON")
	private String lastRunOn;

	@Column(name = "SCHEDULE_ID")
	private String scheduleId;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
		return id;
	}

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
		this.id = id;
	}

    /**
     * Gets job name.
     *
     * @return the job name
     */
    public String getJobName() {
		return jobName;
	}

    /**
     * Sets job name.
     *
     * @param jobName the job name
     */
    public void setJobName(String jobName) {
		this.jobName = jobName;
	}

    /**
     * Gets triggered by.
     *
     * @return the triggered by
     */
    public String getTriggeredBy() {
		return triggeredBy;
	}

    /**
     * Sets triggered by.
     *
     * @param triggeredBy the triggered by
     */
    public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

    /**
     * Gets job type.
     *
     * @return the job type
     */
    public String getJobType() {
		return jobType;
	}

    /**
     * Sets job type.
     *
     * @param jobType the job type
     */
    public void setJobType(String jobType) {
		this.jobType = jobType;
	}

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
		return status;
	}

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
		this.status = status;
	}

    /**
     * Gets last run on.
     *
     * @return the last run on
     */
    public String getLastRunOn() {
		return lastRunOn;
	}

    /**
     * Sets last run on.
     *
     * @param lastRunOn the last run on
     */
    public void setLastRunOn(String lastRunOn) {
		this.lastRunOn = lastRunOn;
	}

    /**
     * Gets schedule id.
     *
     * @return the schedule id
     */
    public String getScheduleId() {
		return scheduleId;
	}

    /**
     * Sets schedule id.
     *
     * @param scheduleId the schedule id
     */
    public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return "Job{" +
				"id='" + id + '\'' +
				", jobName='" + jobName + '\'' +
				", triggeredBy='" + triggeredBy + '\'' +
				", jobType='" + jobType + '\'' +
				", status='" + status + '\'' +
				", lastRunOn=" + lastRunOn +
				", scheduleId='" + scheduleId + '\'' +
				'}';
	}
}
