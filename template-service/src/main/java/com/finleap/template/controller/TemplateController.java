package com.finleap.template.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.finleap.template.exception.AsyncException;
import com.finleap.template.resp.ResponseToClient;
import com.finleap.template.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 * com.finleap.template.controller
 *
 * @author Kalidass Mahalingam 12/2/2019
 */
@RestController
@RequestMapping("/template-service")
public class TemplateController {

    private final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    /**
     * The Template service.
     */
    @Autowired
    TemplateService templateService;

    /**
     * Gets template by id.
     *
     * @param ids the ids
     * @return the template by id
     */
    @Async
    @RequestMapping(value = "/getTemplateById", method = RequestMethod.GET)
    public CompletableFuture<JsonNode> getTemplateById(@RequestParam String ids) {
        logger.info("getTemplateById................");
        return templateService.getTemplateById(ids).thenApply(user -> ResponseToClient.objectToClient(user)).toCompletableFuture();
    }

    /**
     * Gets template by key.
     *
     * @param templateKey the template key
     * @return the template by key
     */
    @Async
    @RequestMapping(value = "/getTemplateByKey", method = RequestMethod.GET)
    public CompletableFuture<JsonNode> getTemplateByKey(@RequestParam String templateKey) {
        logger.info("getTemplateByKey................");
        return templateService.getTemplateByKey(templateKey).thenApply(user -> ResponseToClient.objectToClient(user)).toCompletableFuture();
    }

    @Async
    @RequestMapping(value = "/getAllTemplate", method = RequestMethod.GET)
    public CompletableFuture<JsonNode> getAllTemplate() {
        logger.info("getTemplateById................");
        return templateService.getAllTemplate().thenApply(user -> ResponseToClient.objectToClient(user)).toCompletableFuture();
    }


}
