package com.finleap.notification.worker;

import com.finleap.notification.entity.NotificationTemplate;
import com.finleap.notification.entity.Template;
import com.finleap.notification.entity.User;
import com.finleap.notification.resp.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        List<User> allUserData = getAllUserData();
        System.out.println("allUserData.toString() = " + allUserData.toString());
        List<Template> templateData = getTemplateByKey("SubscribedUserTemplate");
        System.out.println("templateData.toString() = " + templateData.toString());
        enrichData(notificationTemplate, allUserData, templateData);
    }

    private void enrichData(NotificationTemplate notificationTemplate, List<User> userData, List<Template> templateData) {
        Template template = templateData.get(0);
        String body = template.getBody();
        userData = userData.stream()
                .filter(user -> !user.getSubscribedNewsletter())
                .map(user -> enrichBody(user, body))
                .collect(Collectors.toList());
        notificationTemplate.setUserList(userData);
        notificationTemplate.setTemplate(template);
    }
}
