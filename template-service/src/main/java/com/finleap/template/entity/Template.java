package com.finleap.template.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Encapsulates the information about user's situation.
 */
@Entity
@Table(name = "Template")
public class Template implements Serializable {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "template_key")
    private String templateKey;

    @Column(name = "body",  length = 50000)
    private String body;

    @Column(name = "status")
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
