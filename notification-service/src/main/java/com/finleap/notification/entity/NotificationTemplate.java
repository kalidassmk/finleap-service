package com.finleap.notification.entity;

import java.util.List;

/**
 * com.finleap.notification.entity
 *
 * @author Kalidass Mahalingam
 * 17/2/2019
 */
public class NotificationTemplate {


    private String jobId;

    private String jobQueueId;

    private String jobName;

    private String scheduleId;

   private List<User> userList;

   private  Template template;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }


    public String getJobQueueId() {
        return jobQueueId;
    }

    public void setJobQueueId(String jobQueueId) {
        this.jobQueueId = jobQueueId;
    }

    @Override
    public String toString() {
        return "NotificationTemplate{" +
                "jobId='" + jobId + '\'' +
                ", jobQueueId='" + jobQueueId + '\'' +
                ", jobName='" + jobName + '\'' +
                ", scheduleId='" + scheduleId + '\'' +
                ", userList=" + userList +
                ", template=" + template +
                '}';
    }
}
