package com.finleap.notification.resp;

import java.io.Serializable;

/**
 * The type Response.
 *
 * @param <T> the type parameter
 * @author Kalidass Mahalingam
 */
public class Response<T> implements Serializable {
    private T payLoad = null;
    private Status status = null;

    /**
     * Instantiates a new Response.
     */
    public Response() {
    }

    /**
     * Gets pay load.
     *
     * @return the pay load
     */
    public T getPayLoad() {
        return this.payLoad;
    }

    /**
     * Sets pay load.
     *
     * @param payLoad the pay load
     */
    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Response{" +
                "payLoad=" + payLoad +
                ", status=" + status +
                '}';
    }
}
