package com.finleap.notification.worker;

import com.finleap.notification.entity.NotificationTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * com.finleap.notification.worker
 *
 * @author Kalidass Mahalingam
 * 13/2/2019
 */
@Component
@Qualifier("welcomeNotificationJob")
@PropertySource("classpath:application.properties")
public class WelcomeNotificationJob extends JobWorker {



    @Override
    public void execute(NotificationTemplate notificationTemplate) {

    }
}
