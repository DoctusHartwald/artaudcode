package org.springbyexample.jms;

import org.springframework.stereotype.Component;

@Component
public class SetterMessage {

    private String message = null;

    /**
     * Gets message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
