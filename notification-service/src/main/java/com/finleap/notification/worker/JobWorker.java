package com.finleap.notification.worker;

import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.entity.NotificationTemplate;
import com.finleap.notification.mail.EmailService;
import com.finleap.notification.repository.JobRepo;
import com.finleap.notification.resp.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JobWorker extends AbstractJobWorker {

    @Autowired
    JobRepo jobRepo;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EmailService emailService;

    @Value("${user.service.protocol}")
    protected String userServiceProtocol;

    @Value("${user.service.url}")
    protected String userServiceURL;

    @Value("${user.service.port}")
    protected String userServicePort;


    @Value("${template.service.protocol}")
    protected String templateServiceProtocol;

    @Value("${template.service.url}")
    protected String templateServiceURL;

    @Value("${template.service.port}")
    protected String templateServicePort;

    public void run(JobQueue job) {
        System.out.println("JobWorker = " + jobRepo);
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        execute(notificationTemplate);
        sendEmail(notificationTemplate);
        updateStatus(notificationTemplate);
    }

    @Override
    public void execute(NotificationTemplate notificationTemplate) {
        //
    }

    @Override
    public void sendEmail(NotificationTemplate notificationTemplate) {
        emailService.sendMail(notificationTemplate);
    }


    public Response getAllUserData(){
        StringBuffer userService = new StringBuffer();
        userService.append(userServiceProtocol)
                .append("://").append(userServiceURL)
                .append(":").append(userServicePort).append("/")
                .append("user-service").append("/").append("getAllUser");
        Response json = restTemplate.getForObject(userService.toString(), Response.class);
        return json;
    }


    public Response getAllTemplate(){
        StringBuffer templateService = new StringBuffer();
        templateService.append(templateServiceProtocol)
                .append("://").append(templateServiceURL)
                .append(":").append(templateServicePort)
                .append("template-service").append("/")
                .append("getAllTemplate");
        return restTemplate.getForObject(templateService.toString(), Response.class);
    }

    public Response getTemplateByKey(String key) {
        StringBuffer templateService = new StringBuffer();
        templateService.append(templateServiceProtocol)
                .append("://").append(templateServiceURL)
                .append(":").append(templateServicePort)
                .append("template-service").append("/")
                .append("getTemplateByKey").append("?templateKey=").append(key);

        Response json = restTemplate.getForObject(templateService.toString(), Response.class);
        return json;
    }

    public Response getTemplateById() {
        return null;
    }



    @Override
    public void updateStatus(NotificationTemplate notificationTemplate) {

    }

}
