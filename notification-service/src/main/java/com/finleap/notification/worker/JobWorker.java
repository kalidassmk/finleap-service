package com.finleap.notification.worker;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finleap.notification.entity.JobQueue;
import com.finleap.notification.entity.NotificationTemplate;
import com.finleap.notification.entity.Template;
import com.finleap.notification.entity.User;
import com.finleap.notification.mail.EmailService;
import com.finleap.notification.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Value("${user.salutation.regex}")
    protected String userSalutationRegex;

    @Value("${user.name.regex}")
    protected String userNameRegex;

    @Value("${user.identifier.regex}")
    protected String userIdentifierRegex;

    @Value("${apostrophe.regex}")
    protected String apostrophe;


    public void run(JobQueue job) {
        System.out.println("JobWorker = " + jobRepo);
        NotificationTemplate notificationTemplate = new NotificationTemplate();
        notificationTemplate.setJobQueueId(job.getId());
        notificationTemplate.setJobName(job.getJobName());
        notificationTemplate.setJobId(job.getJobId());

        execute(notificationTemplate);
        sendEmail(notificationTemplate);
        updateStatus(notificationTemplate.getJobQueueId());
    }

    @Override
    public void execute(NotificationTemplate notificationTemplate) {
        //
    }

    @Override
    public void sendEmail(NotificationTemplate notificationTemplate) {
        emailService.sendMail(notificationTemplate);
    }


    public List<User> getAllUserData() {
        try {
            StringBuffer userService = new StringBuffer();
            userService.append(userServiceProtocol)
                    .append("://").append(userServiceURL)
                    .append(":").append(userServicePort).append("/")
                    .append("user-service").append("/").append("getAllUser");

            ResponseEntity<String> response = restTemplate.getForEntity(userService.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode payLoad = root.path("payLoad");
            List<User> userList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<User>>() {});

            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Template> getAllTemplate() {
        try {
            StringBuffer templateService = new StringBuffer();
            templateService.append(templateServiceProtocol)
                    .append("://").append(templateServiceURL)
                    .append(":").append(templateServicePort)
                    .append("template-service").append("/")
                    .append("getAllTemplate");

            ResponseEntity<String> response = restTemplate.getForEntity(templateService.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode payLoad = root.path("payLoad");
            List<Template> templateList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<Template>>() {});
            return templateList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Template> getTemplateByKey(String key) {
        try {
            StringBuffer templateService = new StringBuffer();
            templateService.append(templateServiceProtocol)
                    .append("://").append(templateServiceURL)
                    .append(":").append(templateServicePort)
                    .append("template-service").append("/")
                    .append("getTemplateByKey").append("?templateKey=").append(key);

            ResponseEntity<String> response = restTemplate.getForEntity(templateService.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode payLoad = root.path("payLoad");
            List<Template> templateList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<Template>>() {});
            return templateList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Template> getTemplateById() {
        return null;
    }

    public User enrichBody(User user, String body) {
        body = body.replaceAll("}","");
        body =  body.replaceAll("\\{","");
        body = replaceUserSalutation(getUserSalutation(user.getGender()), body);
        body = replaceUserName(user.getFirst_name() + " " + user.getSureName(), body);
        body = replaceUserIdentifier(user.getEmail(), body);
        body = replaceApostrophe(body);
        user.setBody(body);
        return user;
    }

    public String replaceUserSalutation(String salutation, String body) {
        body = body.replaceAll(userSalutationRegex, salutation);
        return body;
    }

    public String replaceUserName(String userName, String body) {
        body = body.replaceAll(userNameRegex, userName);
        return body;
    }

    public String replaceUserIdentifier(String userIdentifier, String body) {
        body = body.replaceAll(userIdentifierRegex, userIdentifier);
        return body;
    }

    public String replaceApostrophe(String body) {
        body = body.replaceAll(userIdentifierRegex, "'");
        return body;
    }

    public String getUserSalutation(String gender) {
        if ("male".equals(gender)) {
            return "Sir";
        } else {
            return "Madam";
        }
    }

    @Override
    public void updateStatus(String jobQueueId) {
    jobRepo.updateJobQueue("COMPLETED", jobQueueId);
    }

}
