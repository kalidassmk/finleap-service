package com.finleap.template.service;


import com.finleap.template.entity.Template;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * The interface Template service.
 *
 * @author Kalidass Mahalingam
 */
public interface TemplateService {

	/**
	 * Gets template by id.
	 *
	 * @param ids the ids
	 * @return the template by id
	 */
	CompletionStage<List<Template>> getTemplateById(String ids);

	/**
	 * Gets template by key.
	 *
	 * @param templateKey the template key
	 * @return the template by key
	 */
	CompletionStage<List<Template>> getTemplateByKey(String templateKey);

	CompletionStage<List<Template>> getAllTemplate();



}
