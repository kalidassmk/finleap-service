package com.finleap.notification.entity;

/**
 * Encapsulates the information about user's situation.
 */

public class User {

    private int     id;

    private String  sureName;

    private String  first_name;

    private String  gender;

    private String email;

    private boolean subscribedNewsletter;

    private String body;



    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets sure name.
     *
     * @return the sure name
     */
    public String getSureName() {
        return sureName;
    }

    /**
     * Sets sure name.
     *
     * @param sureName the sure name
     */
    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets gender.
     *
     * @param gender the gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

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
     * Gets subscribed newsletter.
     *
     * @return the subscribed newsletter
     */
    public boolean getSubscribedNewsletter() {
        return subscribedNewsletter;
    }

    /**
     * Sets subscribed newsletter.
     *
     * @param subscribedNewsletter the subscribed newsletter
     */
    public void setSubscribedNewsletter(boolean subscribedNewsletter) {
        this.subscribedNewsletter = subscribedNewsletter;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", sureName='" + sureName + '\'' +
                ", first_name='" + first_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", subscribedNewsletter=" + subscribedNewsletter +
                ", body='" + body + '\'' +
                '}';
    }
}
