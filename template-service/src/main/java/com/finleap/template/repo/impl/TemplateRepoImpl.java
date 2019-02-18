package com.finleap.template.repo.impl;


import com.finleap.template.entity.Template;
import com.finleap.template.repo.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * The type Template repo.
 *
 * @author Kalidass Mahalingam
 */
@Repository("TemplateRepo")
public class TemplateRepoImpl implements TemplateRepo {

    private static final String TEMPLATE_BY_ID_QUERY = "SELECT ID,STATUS,BODY,template_key FROM TEMPLATE WHERE ID in(:ids)";

    private static final String TEMPLATE_BY_KEY_QUERY = "SELECT ID,STATUS,BODY,template_key FROM TEMPLATE WHERE template_key in (:templateKeys)";

    private static final String TEMPLATE_ALL_QUERY = "SELECT ID,STATUS,BODY,template_key FROM TEMPLATE WHERE status in(:status)";


    /**
     * The Named parameter jdbc template.
     */
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public CompletionStage<List<Template>> getTemplateById(String ids) {
        return	CompletableFuture.supplyAsync(() -> {
            List<String> inParam = Arrays.asList(ids.split("\\s*,\\s*"));
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("ids", inParam);
            List<Template> template = namedParameterJdbcTemplate.query(TEMPLATE_BY_ID_QUERY, namedParameters, new TemplateRowMapper());
            return template;
        });
    }

    @Override
    public CompletionStage<List<Template>> getTemplateByKey(String templateKey) {
        return	CompletableFuture.supplyAsync(() -> {
            List<String> inParam = Arrays.asList(templateKey.split("\\s*,\\s*"));
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("templateKeys", inParam);
            List<Template> template = namedParameterJdbcTemplate.query(TEMPLATE_BY_KEY_QUERY, namedParameters, new TemplateRowMapper());
            return template;
        });

    }

    @Override
    public CompletionStage<List<Template>> getAllTemplate() {
        return	CompletableFuture.supplyAsync(() -> {
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", "ACTIVE");
            List<Template> template = namedParameterJdbcTemplate.query(TEMPLATE_BY_KEY_QUERY, namedParameters, new TemplateRowMapper());
            return template;
        });
    }

    /**
     * The type User row mapper.
     */
    public static class TemplateRowMapper implements RowMapper {

        /**
         * The Body.
         */
        static final String BODY = "BODY";
        /**
         * The Id.
         */
        static final String ID = "ID";
        /**
         * The Status.
         */
        static final String STATUS = "STATUS";
        /**
         * The Templatekey.
         */
        static final String TEMPLATEKEY = "template_key";

        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            Template template = new Template();
            template.setBody(rs.getString(BODY));
            template.setId(rs.getString(ID));
            template.setTemplateKey(rs.getString(TEMPLATEKEY));
            template.setStatus(rs.getString(STATUS));

            return template;
        }
    }

}
