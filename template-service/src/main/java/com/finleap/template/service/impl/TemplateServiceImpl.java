package com.finleap.template.service.impl;

import com.finleap.template.entity.Template;
import com.finleap.template.repo.TemplateRepo;
import com.finleap.template.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;


/**
 * The type Template service.
 *
 * @author Kalidass Mahalingam
 */
@Service("TemplateService")
public class TemplateServiceImpl implements TemplateService {

    /**
     * The Home repo.
     */
    @Autowired
    TemplateRepo homeRepo;

    @Override
    public CompletionStage<List<Template>> getTemplateById(String ids ) {
        return homeRepo.getTemplateById(ids);
    }

    @Override
    public CompletionStage<List<Template>> getTemplateByKey(String templateKey) {
        return homeRepo.getTemplateByKey(templateKey);
    }

    public CompletionStage<List<Template>> getAllTemplate( ) {
        return homeRepo.getAllTemplate();
    }

}
