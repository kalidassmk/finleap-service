package com.finleap.notification.model;

import java.sql.Timestamp;

public class JobHistory {
	
	public static final String JOB_STATUS_COMPLETED = "completed";
	public static final String JOB_STATUS_PARTIAL = "partial";
	public static final String JOB_STATUS_FAILED = "failed";
	public static final String JOB_STATUS_INPROGRESS = "inprogress";

	private String id;
	
	private String scheduleId = null;
	
	private String jobId = null;
	
	private String jobName = null;

	private String status = null;

	private Timestamp startTime = null;

	private Timestamp endTime = null;

	private String info = null;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



}
