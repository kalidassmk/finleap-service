package com.finleap.notification.repository;

import com.finleap.notification.entity.Job;
import com.finleap.notification.entity.JobQueue;

/**
 * com.finleap.notification.repository
 *
 * @author Kalidass Mahalingam 13/2/2019
 */
public interface JobRepo {

    /**
     * Gets job by schedule id.
     *
     * @param scheduleId the schedule id
     * @return the job by schedule id
     */
    Job getJobByScheduleId(String scheduleId);

    /**
     * Create job queue.
     *
     * @param jobQueue the job queue
     */
    void createJobQueue(JobQueue jobQueue);

    /**
     * Gets job queue.
     *
     * @return the job queue
     */
    JobQueue getJobQueue();

    /**
     * Update job queue.
     *
     * @param status     the status
     * @param jobQueueId the job queue id
     */
    void updateJobQueue(String status, String jobQueueId);

}
