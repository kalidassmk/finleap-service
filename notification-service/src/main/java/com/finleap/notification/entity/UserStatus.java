package com.finleap.notification.entity;

import java.io.Serializable;

/**
 *
 * @author Kalidass Mahalingam 12/2/2019
 */

public class UserStatus implements Serializable {



    private Integer id;


    private String email;

    private String templateId;

    private String timeStamp;

    private String status;

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets mail type.
     *
     * @return the mail type
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * Sets mail type.
     *
     * @param templateId the mail type
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }


    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets time stamp.
     *
     * @param timeStamp the time stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
