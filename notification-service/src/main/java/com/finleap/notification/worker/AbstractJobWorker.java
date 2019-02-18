package com.finleap.notification.worker;

import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.entity.NotificationTemplate;

public abstract class AbstractJobWorker {

    public abstract void run(JobQueue job);

    public abstract void execute(NotificationTemplate notificationTemplate);

    public abstract void updateStatus(NotificationTemplate notificationTemplate);

    public abstract void sendEmail(NotificationTemplate notificationTemplate);

}
