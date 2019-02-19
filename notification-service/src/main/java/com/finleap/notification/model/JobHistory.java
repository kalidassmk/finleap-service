package com.finleap.notification.model;

import java.sql.Timestamp;

/**
 * The type Job history.
 */
public class JobHistory {

	/**
	 * The constant JOB_STATUS_COMPLETED.
	 */
	public static final String JOB_STATUS_COMPLETED = "completed";
	/**
	 * The constant JOB_STATUS_PARTIAL.
	 */
	public static final String JOB_STATUS_PARTIAL = "partial";
	/**
	 * The constant JOB_STATUS_FAILED.
	 */
	public static final String JOB_STATUS_FAILED = "failed";
	/**
	 * The constant JOB_STATUS_INPROGRESS.
	 */
	public static final String JOB_STATUS_INPROGRESS = "inprogress";

	private String id;
	
	private String scheduleId = null;
	
	private String jobId = null;
	
	private String jobName = null;

	private String status = null;

	private Timestamp startTime = null;

	private Timestamp endTime = null;

	private String info = null;


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

	/**
	 * Gets job id.
	 *
	 * @return the job id
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Sets job id.
	 *
	 * @param jobId the job id
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
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
	 * Gets start time.
	 *
	 * @return the start time
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * Sets start time.
	 *
	 * @param startTime the start time
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets end time.
	 *
	 * @return the end time
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * Sets end time.
	 *
	 * @param endTime the end time
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets info.
	 *
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets info.
	 *
	 * @param info the info
	 */
	public void setInfo(String info) {
		this.info = info;
	}



}
