package com.finleap.notification.mail;

import com.finleap.notification.entity.NotificationTemplate;

import java.util.concurrent.CompletionStage;

/**
 * Interface for emails service.
 */
public interface EmailService {

    /**
     * Will send an email to a recipient.
     *
     * @param notificationTemplate recipient
     * @return the completion stage
     */
    CompletionStage<Boolean> sendMail(NotificationTemplate notificationTemplate);

}
