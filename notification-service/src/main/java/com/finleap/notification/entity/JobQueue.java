package com.finleap.notification.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


/**
 * The type Job queue.
 */
@Entity
@Table(name = "JOB_QUEUE")
public class JobQueue {
	
	public static final String JOB_QUEUE_STATUS_NEW = "NEW";

	@Id
	private String id ;
	
	private String status;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	private Date completedOn;

	@Column(name = "INSTANCE_NAME")
	private String instanceName;

	@Column(name = "JOB_NAME")
	private String jobName;

	@Column(name = "JOB_TYPE")
	private String jobType;

	@Column(name = "JOB_ID")
	private String jobId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}
