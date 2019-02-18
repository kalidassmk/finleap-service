package com.finleap.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Job")
public class Job {
	
	public static final String JOB_STATUS_COMPLETED = "COMPLETED";
	public static final String JOB_TRIGGERED_BY_SCHEDULER = "BY_SCHEDULER";
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTriggeredBy() {
		return triggeredBy;
	}

	public void setTriggeredBy(String triggeredBy) {
		this.triggeredBy = triggeredBy;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastRunOn() {
		return lastRunOn;
	}

	public void setLastRunOn(String lastRunOn) {
		this.lastRunOn = lastRunOn;
	}

	public String getScheduleId() {
		return scheduleId;
	}

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
