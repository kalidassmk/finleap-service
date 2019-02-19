package com.finleap.notification.actor;

import akka.actor.UntypedActor;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.worker.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.finleap.notification.actor.JobQueueActor.statusMap;

/**
 * The type Job actor.
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobActor extends UntypedActor {

    /**
     * The Subscribed notification job.
     */
    JobWorker subscribedNotificationJob;

    /**
     * The Welcome notification job.
     */
    JobWorker welcomeNotificationJob;

    /**
     * The Rest template.
     */
    RestTemplate restTemplate;

    private final Logger logger = LoggerFactory.getLogger(JobActor.class);


    /**
     * Instantiates a new Job actor.
     *
     * @param subscribedNotificationJob the subscribed notification job
     * @param welcomeNotificationJob    the welcome notification job
     * @param restTemplate              the rest template
     */
    public JobActor(@Qualifier("subscribedNotificationJob") JobWorker subscribedNotificationJob,
                    @Qualifier("welcomeNotificationJob") JobWorker welcomeNotificationJob,
                    @Qualifier("restTemplate") RestTemplate restTemplate) {
        this.subscribedNotificationJob = subscribedNotificationJob;
        this.welcomeNotificationJob = welcomeNotificationJob;
        this.restTemplate = restTemplate;
    }

    @Override
    public void onReceive(Object arg0) {
        logger.info("Job Actor onReceive message..............");
        if (arg0 instanceof JobQueue) {
            JobQueue jobQueue = (JobQueue) arg0;
            logger.info("Job name {} ..............", jobQueue.getJobName());
            if ("SubscribedNotificationJob".equals(jobQueue.getJobName()))
                subscribedNotificationJob.run(jobQueue);
            else if ("WelcomeNotificationJob".equals(jobQueue.getJobName()))
                welcomeNotificationJob.run(jobQueue);
            else
                logger.info("Job  name does not define..................." + jobQueue.getJobName());

        }

        statusMap.put(this.getSelf(), "ready");
    }

}
