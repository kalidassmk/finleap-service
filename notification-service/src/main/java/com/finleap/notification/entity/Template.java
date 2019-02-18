package com.finleap.notification.entity;

/**
 * Encapsulates the information about user's situation.
 */

public class Template {

    private String id;

    private String templateKey;

    private String body;

    private String status;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets template key.
     *
     * @return the template key
     */
    public String getTemplateKey() {
        return templateKey;
    }

    /**
     * Sets template key.
     *
     * @param templateKey the template key
     */
    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets body.
     *
     * @param body the body
     */
    public void setBody(String body) {
        this.body = body;
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

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", templateKey='" + templateKey + '\'' +
                ", body='" + body + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
