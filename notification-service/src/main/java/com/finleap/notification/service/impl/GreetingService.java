package com.finleap.notification.service.impl;

import org.springframework.stereotype.Component;

/**
 * com.finleap.notification.service.impl
 *
 * @author Kalidass Mahalingam
 * 16 Feb 2019
 */

@Component
public class GreetingService {

    public String greet(String name) {
        return "Hello, " + name;
    }

}