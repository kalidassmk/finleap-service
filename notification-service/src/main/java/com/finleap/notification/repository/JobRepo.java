package com.finleap.notification.repository;

import com.finleap.notification.entity.Job;
import com.finleap.notification.entity.JobQueue;

/**
 * com.finleap.notification.repository
 *
 * @author Kalidass Mahalingam
 * 13/2/2019
 */

public interface JobRepo {

    Job getJobByScheduleId(String scheduleId);

    void createJobQueue(JobQueue jobQueue);

    JobQueue getJobQueue();

    void updateJobQueue(String status, String jobQueueId);

}
