package com.finleap.user.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.finleap.user.entity.User;
import com.finleap.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

import static com.finleap.user.resp.ResponseToClient.listToClient;
import static com.finleap.user.resp.ResponseToClient.objectToClient;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * com.finleap.user.controller
 *
 * @author Kalidass Mahalingam 11/2/2019
 */
@RestController
@RequestMapping("/user-service")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * Gets all user.
     *
     * @return the all user
     */
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> getAllUser() {
        logger.info("getAllUser................");
        return userService.getAllUser().thenApply(user -> listToClient(user)).toCompletableFuture();
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> getUserById(@RequestParam int id) {
        logger.info("getUserById................");
        return userService.getUser(id).thenApply(user -> objectToClient(user)).toCompletableFuture();
    }

    /**
     * Save completable future.
     *
     * @param user the user
     * @return the completable future
     */
    @RequestMapping(value = "/saveUser", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> save(@RequestBody User user) {
        logger.info("save................");
        return userService.save(user).thenApply(userResp -> objectToClient(userResp)).toCompletableFuture();
    }

}
