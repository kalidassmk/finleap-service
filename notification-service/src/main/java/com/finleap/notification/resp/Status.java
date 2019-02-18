package com.finleap.notification.resp;

import java.io.Serializable;

/**
 * The type Status.
 *
 * @author Kalidass Mahalingam
 */
public class Status  implements Serializable {
    /**
     * The Type.
     */
    protected Object type;
    /**
     * The Cause.
     */
    protected Object cause;

    /**
     * Instantiates a new Status.
     */
    public Status() {
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Object getType() {
        return this.type;
    }


    /**
     * Gets cause.
     *
     * @return the cause
     */
    public Object getCause() {
        return this.cause;
    }


}