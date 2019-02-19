package com.finleap.notification.mail;

import com.finleap.notification.entity.NotificationTemplate;
import com.finleap.notification.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * This class help to send the user email
 *
 * @author Kalidass Mahalingam 16/2/2019
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * The Batch size.
     */
    @Value("${email.batch.size}")
    int batchSize;

    @Override
    public CompletionStage<Boolean> sendMail(NotificationTemplate notificationTemplate) {
        return CompletableFuture.supplyAsync(() -> {
            LOGGER.info("Batch update has started.............................");
            LOGGER.info("Batch size : " + batchSize);
            for (int i = 0; i < notificationTemplate.getUserList().size(); i += batchSize) {
                int end = Math.min(notificationTemplate.getUserList().size(), i + batchSize);
                send(notificationTemplate.getUserList().subList(i, end));
            }
            return Boolean.TRUE;
        });

    }


    /**
     * Utility method to send simple HTML email
     */
    private static Boolean send(List<User> userList) {
        try {
            LOGGER.info("Email Sent !!!!!!!!!!!!!!!!!!!!!!");
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}

