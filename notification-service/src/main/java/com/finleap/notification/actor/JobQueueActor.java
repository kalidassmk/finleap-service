package com.finleap.notification.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.finleap.notification.container.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobQueueActor extends UntypedActor {

    @Autowired
    private ActorSystem system;

    JobRepo jobRepo;

    public static Map<ActorRef, String> statusMap = new HashMap<>();

    int jobActorCount = 5;

    public JobQueueActor(JobRepo jobRepo) {
        this.jobRepo = jobRepo;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        for (int i = 0; i < jobActorCount; i++) {
            ActorRef jobActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("jobActor"), "jobActor-" + i);
            statusMap.put(jobActor, "ready");
        }

    }

    @Override
    public void onReceive(Object arg0) {
        System.out.println("Job Queue ..................................");
        JobQueue jobQueue = jobRepo.getJobQueue();
        if (jobQueue != null) {
            ActorRef actorRef = anyReadyActor();
            if (actorRef != null) {
                actorRef.tell(jobQueue, null);
                statusMap.put(actorRef, "busy");
            } else {
                System.out.println("All Workers are busy..............");
            }
        }
    }


    public ActorRef anyReadyActor() {
        return statusMap.entrySet().stream().filter(actor -> "ready".equals(actor.getValue())).findFirst().get().getKey();
    }


}