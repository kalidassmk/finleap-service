package com.finleap.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finleap.notification.service.SchedulerService;

@RestController
public class SchedulerController {

	@Autowired
    SchedulerService schedulerService;
	
	@RequestMapping("/startScheduler")
	public boolean startScheduler() {
		return schedulerService.startScheduler();
	}

}
