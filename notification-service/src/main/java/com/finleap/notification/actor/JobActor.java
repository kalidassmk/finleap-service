package com.finleap.notification.actor;

import akka.actor.UntypedActor;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.worker.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.finleap.notification.actor.JobQueueActor.statusMap;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobActor extends UntypedActor {

	JobWorker subscribedNotificationJob;

	JobWorker welcomeNotificationJob;

	RestTemplate restTemplate;

	public JobActor(@Qualifier("subscribedNotificationJob") JobWorker subscribedNotificationJob,
					@Qualifier("welcomeNotificationJob") JobWorker welcomeNotificationJob,
					@Qualifier("restTemplate") RestTemplate restTemplate) {
		this.subscribedNotificationJob = subscribedNotificationJob;
		this.welcomeNotificationJob = welcomeNotificationJob;
		this.restTemplate=restTemplate;
	}

	@Override
	public void onReceive(Object arg0) {
		System.out.println("Job Actor onReceive ..............");
		if (arg0 instanceof JobQueue) {
			JobQueue jobQueue = (JobQueue) arg0;
			if ("SubscribedNotificationJob".equals(jobQueue.getJobName()))
				subscribedNotificationJob.run(jobQueue);
			else if ("WelcomeNotificationJob".equals(jobQueue.getJobName()))
				welcomeNotificationJob.run(jobQueue);
			else
				System.out.println("Job does not define..................." + jobQueue.getJobName());

		}

		statusMap.put(this.getSelf(), "ready");
	}

}
