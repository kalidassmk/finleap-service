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

    /**
     * The constant JOB_QUEUE_STATUS_NEW.
     */
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
     * Gets created on.
     *
     * @return the created on
     */
    public Date getCreatedOn() {
		return createdOn;
	}

    /**
     * Sets created on.
     *
     * @param createdOn the created on
     */
    public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

    /**
     * Gets completed on.
     *
     * @return the completed on
     */
    public Date getCompletedOn() {
		return completedOn;
	}

    /**
     * Sets completed on.
     *
     * @param completedOn the completed on
     */
    public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

    /**
     * Gets instance name.
     *
     * @return the instance name
     */
    public String getInstanceName() {
		return instanceName;
	}

    /**
     * Sets instance name.
     *
     * @param instanceName the instance name
     */
    public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
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
}
