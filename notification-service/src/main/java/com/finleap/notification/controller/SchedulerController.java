package com.finleap.notification.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.finleap.notification.resp.Response;
import com.finleap.notification.service.SchedulerService;
import com.finleap.notification.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finleap.notification.resp.ResponseToClient.objectToClient;

/**
 * The type Scheduler controller.
 */
@RestController
public class SchedulerController {

    /**
     * The Scheduler service.
     */
    @Autowired
    SchedulerService schedulerService;

    private final Logger logger = LoggerFactory.getLogger(SchedulerController.class);


    /**
     * Start scheduler json node.
     *
     * @return the json node
     */
    @RequestMapping("/startScheduler")
    public JsonNode startScheduler() {
        logger.info("startScheduler.................");
        return objectToClient(schedulerService.startScheduler());
    }

}
