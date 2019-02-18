package com.finleap.notification.mail;

import com.finleap.notification.entity.NotificationTemplate;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
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

    @Override
    public CompletionStage<Boolean> sendMail(NotificationTemplate notificationTemplate) {
        return CompletableFuture.supplyAsync(() -> {
            send(session, notificationTemplate.getEmail(), getSubject(), getBody(notificationTemplate));
            return Boolean.TRUE;
        });

    }


    /**
     * Utility method to send simple HTML email
     *
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    private static Boolean send(Session session, String toEmail, String subject, String body) {
        try {
          

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getSubject() {
        return "";
    }


    /**
     * Gets body.
     *
     * @param notificationTemplate the mail type
     * @return the body
     */
    public String getBody(NotificationTemplate notificationTemplate) {

        return null;

    }

}

