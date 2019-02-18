package com.finleap.notification.actor;

import akka.actor.UntypedActor;
import com.finleap.notification.entity.Job;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.entity.Schedule;
import com.finleap.notification.repository.JobRepo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Random;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SchedulerActor extends UntypedActor {

    JobRepo jobRepo;

    public SchedulerActor(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public void onReceive(Object arg0) {

        if (arg0 instanceof Schedule) {

            Schedule schedule = (Schedule) arg0;
            Job job = jobRepo.getJobByScheduleId(schedule.getId());

            if (job != null) {
                JobQueue jobQueue = new JobQueue();
                jobQueue.setJobId(job.getId());
                jobQueue.setJobName(job.getJobName());
                jobQueue.setJobType(job.getJobType());
                jobQueue.setId(String.valueOf(new Random().nextInt()));
                jobQueue.setCreatedOn(new Date(System.currentTimeMillis()));
                jobQueue.setStatus("NEW");
                jobRepo.createJobQueue(jobQueue);
            }

        }
        System.out.println("SchedulerActor..................");

    }



}
