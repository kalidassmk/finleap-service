package com.finleap.notification.entity;

import java.util.List;

/**
 * com.finleap.notification.entity
 *
 * @author Kalidass Mahalingam 17/2/2019
 */
public class NotificationTemplate {


    private String jobId;

    private String jobQueueId;

    private String jobName;

    private String scheduleId;

   private List<User> userList;

   private  Template template;


    /**
     * Gets job id.
     *
     * @return the job id
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * Sets job id.
     *
     * @param jobId the job id
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * Gets job name.
     *
     * @return the job name
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * Sets job name.
     *
     * @param jobName the job name
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * Gets schedule id.
     *
     * @return the schedule id
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets schedule id.
     *
     * @param scheduleId the schedule id
     */
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Sets user list.
     *
     * @param userList the user list
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Gets template.
     *
     * @return the template
     */
    public Template getTemplate() {
        return template;
    }

    /**
     * Sets template.
     *
     * @param template the template
     */
    public void setTemplate(Template template) {
        this.template = template;
    }


    /**
     * Gets job queue id.
     *
     * @return the job queue id
     */
    public String getJobQueueId() {
        return jobQueueId;
    }

    /**
     * Sets job queue id.
     *
     * @param jobQueueId the job queue id
     */
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
