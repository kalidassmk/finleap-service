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

import static com.finleap.template.resp.ResponseToClient.listToClient;
import static com.finleap.template.resp.ResponseToClient.objectToClient;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    @RequestMapping(value = "/getTemplateById", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> getTemplateById(@RequestParam String ids) {
        logger.info("getTemplateById................");
        return templateService.getTemplateById(ids).thenApply(template -> listToClient(template)).toCompletableFuture();
    }

    /**
     * Gets template by key.
     *
     * @param templateKey the template key
     * @return the template by key
     */
    @RequestMapping(value = "/getTemplateByKey", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> getTemplateByKey(@RequestParam String templateKey) {
        logger.info("getTemplateByKey................");
        return templateService.getTemplateByKey(templateKey).thenApply(template -> listToClient(template)).toCompletableFuture();
    }

    @RequestMapping(value = "/getAllTemplate", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public CompletableFuture<JsonNode> getAllTemplate() {
        logger.info("getTemplateById................");
        return templateService.getAllTemplate().thenApply(template -> listToClient(template)).toCompletableFuture();
    }


}
