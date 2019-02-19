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
import com.finleap.notification.service.impl.SchedulerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

/**
 * The type Job worker.
 */
@Component
public class JobWorker extends AbstractJobWorker {

    /**
     * The Job repo.
     */
    @Autowired
    JobRepo jobRepo;

    /**
     * The Rest template.
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * The Email service.
     */
    @Autowired
    EmailService emailService;

    /**
     * The User service protocol.
     */
    @Value("${user.service.protocol}")
    protected String userServiceProtocol;

    /**
     * The User service url.
     */
    @Value("${user.service.url}")
    protected String userServiceURL;

    /**
     * The User service port.
     */
    @Value("${user.service.port}")
    protected String userServicePort;


    /**
     * The Template service protocol.
     */
    @Value("${template.service.protocol}")
    protected String templateServiceProtocol;

    /**
     * The Template service url.
     */
    @Value("${template.service.url}")
    protected String templateServiceURL;

    /**
     * The Template service port.
     */
    @Value("${template.service.port}")
    protected String templateServicePort;

    /**
     * The User salutation regex.
     */
    @Value("${user.salutation.regex}")
    protected String userSalutationRegex;

    /**
     * The User name regex.
     */
    @Value("${user.name.regex}")
    protected String userNameRegex;

    /**
     * The User identifier regex.
     */
    @Value("${user.identifier.regex}")
    protected String userIdentifierRegex;

    /**
     * The Apostrophe.
     */
    @Value("${apostrophe.regex}")
    protected String apostrophe;

    private final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);



    public void run(JobQueue job) {
        logger.info("JobWorker = " + jobRepo.toString());
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


    /**
     * Gets all user data.
     *
     * @return the all user data
     */
    public List<User> getAllUserData() {
        try {

            logger.info("get All User Data.............. ");

            StringBuffer userService = new StringBuffer();
            userService.append(userServiceProtocol)
                    .append("://").append(userServiceURL)
                    .append(":").append(userServicePort).append("/")
                    .append("user-service").append("/").append("getAllUser");

            ResponseEntity<String> response = restTemplate.getForEntity(userService.toString(), String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode payLoad = root.path("payLoad");
            List<User> userList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<User>>() {
            });

            logger.info(" Get the User Data Completed {} .............. ", userList.size());
            return userList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Gets all template.
     *
     * @return the all template
     */
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
            List<Template> templateList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<Template>>() {
            });

            logger.info(" Get the Template Data Completed {} .............. ", templateList.size());

            return templateList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Gets template by key.
     *
     * @param key the key
     * @return the template by key
     */
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
            List<Template> templateList = new ObjectMapper().readValue(payLoad.toString(), new TypeReference<List<Template>>() {
            });

            logger.info(" Get the Template  by key  Completed {} .............. ", templateList.size());

            return templateList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Gets template by id.
     *
     * @return the template by id
     */
    public List<Template> getTemplateById() {
        return null;
    }

    /**
     * Enrich body user.
     *
     * @param user the user
     * @param body the body
     * @return the user
     */
    public User enrichBody(User user, String body) {
        body = body.replaceAll("}", "");
        body = body.replaceAll("\\{", "");
        body = replaceUserSalutation(getUserSalutation(user.getGender()), body);
        body = replaceUserName(user.getFirst_name() + " " + user.getSureName(), body);
        body = replaceUserIdentifier(user.getEmail(), body);
        body = replaceApostrophe(body);
        user.setBody(body);
        return user;
    }

    /**
     * Replace user salutation string.
     *
     * @param salutation the salutation
     * @param body       the body
     * @return the string
     */
    public String replaceUserSalutation(String salutation, String body) {
        body = body.replaceAll(userSalutationRegex, salutation);
        return body;
    }

    /**
     * Replace user name string.
     *
     * @param userName the user name
     * @param body     the body
     * @return the string
     */
    public String replaceUserName(String userName, String body) {
        body = body.replaceAll(userNameRegex, userName);
        return body;
    }

    /**
     * Replace user identifier string.
     *
     * @param userIdentifier the user identifier
     * @param body           the body
     * @return the string
     */
    public String replaceUserIdentifier(String userIdentifier, String body) {
        body = body.replaceAll(userIdentifierRegex, userIdentifier);
        return body;
    }

    /**
     * Replace apostrophe string.
     *
     * @param body the body
     * @return the string
     */
    public String replaceApostrophe(String body) {
        body = body.replaceAll(userIdentifierRegex, "'");
        return body;
    }

    /**
     * Gets user salutation.
     *
     * @param gender the gender
     * @return the user salutation
     */
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
        logger.info("update job queue status COMPLETED .............. ");
    }

}
