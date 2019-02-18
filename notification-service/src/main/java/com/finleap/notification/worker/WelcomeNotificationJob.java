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
        Response userData = getAllUserData();
        System.out.println("allUserData.toString() = " + userData.toString());
        Response templateData = getTemplateByKey("WelcomeUserTemplate");
        System.out.println("template.toString() = " + templateData.toString());
        enrichData(notificationTemplate, userData, templateData);
    }

    private void enrichData(NotificationTemplate notificationTemplate, Response userData, Response templateData) {
        List<User> userList = (List<User>) userData.getPayLoad();
        Template template = (Template) templateData.getPayLoad();
        String body = template.getBody();
        userList = userList.stream()
                .filter(user -> !user.getSubscribedNewsletter())
                .map(user -> enrichBody(user, body))
                .collect(Collectors.toList());
        notificationTemplate.setUserList(userList);
        notificationTemplate.setTemplate(template);
    }
}
