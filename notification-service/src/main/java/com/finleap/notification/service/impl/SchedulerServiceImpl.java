package com.finleap.notification.service.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import com.finleap.notification.container.SchedulerContainer;
import com.finleap.notification.entity.Schedule;
import com.finleap.notification.repository.JobRepo;
import com.finleap.notification.repository.SchedulerRepo;
import com.finleap.notification.service.SchedulerService;
import com.finleap.notification.util.ScheduleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.finleap.notification.container.SpringExtension.SPRING_EXTENSION_PROVIDER;

/**
 * @author Kalidass Mahalingam
 */
@Service("SchedulerService")
public class SchedulerServiceImpl implements SchedulerService {

    private final JobRepo jobRepo;

    @Autowired
    SchedulerRepo schedulerRepo;

    public boolean isStarted = false;

    @Autowired
    private ActorSystem system;

    private final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);


    @Autowired
    public SchedulerServiceImpl(JobRepo jobRepo, ActorSystem system) {
        this.jobRepo = jobRepo;
        this.system = system;
        startRegistration(jobRepo);
    }

    public boolean startScheduler() {

        if (!isStarted) {
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            Runnable task = () -> {
                startScheduleSync();
            };
            executorService.scheduleWithFixedDelay(task, 0, 5, TimeUnit.MINUTES);
            isStarted = true;
        }
        return true;
    }

    public boolean startScheduleSync() {
        List<Schedule> existingScheduleList = SchedulerContainer.getInstance().synchingExistingScheduler();
        existingScheduleList.stream().forEach(schedule -> {
            Schedule scheduleFromDb = schedulerRepo.getSchedulerById(schedule.getId());
            if ("INACTIVE".equals(scheduleFromDb.getStatus())) {
                removeSchedule(schedule);
            }
        });

        List<Schedule> newSchedule = schedulerRepo.synchingNewSchedule();
        newSchedule.stream().forEach(schedule -> {
            if (SchedulerContainer.getInstance().get(schedule.getId()) != null) {
                if (removeSchedule(schedule)) {
                    if (addSchedule(schedule)) {
                        SchedulerContainer.getInstance().save(schedule);
                    }
                }
            } else {
                if (addSchedule(schedule)) {
                    SchedulerContainer.getInstance().save(schedule);
                }
            }
        });

        System.out.println("startScheduleSync");

        return false;
    }

    public boolean addSchedule(Schedule schedule) {

        try {
            ActorSystem actorSystem = ActorSystem.create("SchedulerService");

            ActorRef scheduleActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("schedulerActor"), "schedulerActor" + schedule.getId());

            Cancellable cancellable = null;
            Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("Asia/Singapore"));
            long delayInSec = ScheduleHelper.nextRunSync(schedule, currentTime);

            System.out.println("delay....................." + delayInSec);
            schedule.setDelay("" + delayInSec);

            FiniteDuration delay = FiniteDuration.create(delayInSec, TimeUnit.SECONDS);
            FiniteDuration frequency = ScheduleHelper.getFrequency(schedule);

            if ("once".equals(schedule.getFrequency())) {
                cancellable = actorSystem.scheduler().scheduleOnce(delay, scheduleActor, schedule,
                        actorSystem.dispatcher(), null);

            } else {
                cancellable = actorSystem.scheduler().schedule(delay, frequency, scheduleActor, schedule,
                        actorSystem.dispatcher(), null);
            }
            schedule.setStatus("ACTIVE");
            schedule.setLastTriggeredOn(new Timestamp(System.currentTimeMillis()));

            SchedulerContainer.getInstance().saveCancellableSchedule(schedule.getId(), cancellable);
            schedulerRepo.updateSchedule(schedule);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean removeSchedule(Schedule schedule) {
        String id = schedule.getId();
        Cancellable cancellable = SchedulerContainer.getInstance().getCancellableSchedule(id);
        if (cancellable != null) {
            cancellable.cancel();
        }
        SchedulerContainer.getInstance().remove(id);
        SchedulerContainer.getInstance().removeCancellableSchedule(id);

        return true;
    }

    public boolean startRegistration(JobRepo jobRepo) {
        ActorRef jobQueueActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("jobQueueActor"), "jobQueueActor");
        system.scheduler().schedule(Duration.create(30, TimeUnit.SECONDS), Duration.create(50, TimeUnit.SECONDS), jobQueueActor, "jobqueue", system.dispatcher(), null);
        return true;
    }

}
