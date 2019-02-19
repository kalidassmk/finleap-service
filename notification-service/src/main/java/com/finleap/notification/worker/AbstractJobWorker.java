package com.finleap.notification.worker;

import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.entity.NotificationTemplate;

/**
 * The type Abstract job worker.
 */
public abstract class AbstractJobWorker {

    /**
     * Run.
     *
     * @param job the job
     */
    public abstract void run(JobQueue job);

    /**
     * Execute.
     *
     * @param notificationTemplate the notification template
     */
    public abstract void execute(NotificationTemplate notificationTemplate);

    /**
     * Update status.
     *
     * @param jobQueueId the job queue id
     */
    public abstract void updateStatus(String jobQueueId);

    /**
     * Send email.
     *
     * @param notificationTemplate the notification template
     */
    public abstract void sendEmail(NotificationTemplate notificationTemplate);

}
