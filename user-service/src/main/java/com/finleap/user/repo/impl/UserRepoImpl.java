package com.finleap.user.repo.impl;


import com.finleap.user.entity.User;
import com.finleap.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * The type User repo.
 *
 * @author Kalidass Mahalingam
 */
@Repository("UserRepo")
public class UserRepoImpl implements UserRepo {

	private static final String USER_QUERY = "SELECT ID,EMAIL,FIRSTNAME,GENDER,SUBSCRIBEDNEWSLETTER,SURENAME FROM USER WHERE ID = :id";

	private static final String ALL_USER_QUERY = "SELECT ID,EMAIL,FIRSTNAME,GENDER,SUBSCRIBEDNEWSLETTER,SURENAME  FROM USER";


	/**
	 * The Named parameter jdbc template.
	 */
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * The Jdbc template object.
	 */
	@Autowired
	JdbcTemplate jdbcTemplateObject;

	@Override
	public CompletionStage<User> getUser(int id) {
		return	CompletableFuture.supplyAsync(() -> {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
			List<User> user = namedParameterJdbcTemplate.query(USER_QUERY, namedParameters, new UserRowMapper());
			return user != null && !user.isEmpty() ? user.get(0) : new User();
		});
		
	}

	@Override
	public CompletionStage<List<User>> getAllUser() {
		return	CompletableFuture.supplyAsync(() -> {
			return jdbcTemplateObject.query(ALL_USER_QUERY,  new UserRowMapper());
		});
	}

	@Override
	public CompletionStage<User> save(User user) {
		return null;
	}

	/**
	 * The type User row mapper.
	 */
	public static class UserRowMapper implements RowMapper {
		/**
		 * The Email.
		 */
		static final String EMAIL = "EMAIL";
		/**
		 * The Has contract.
		 */
		static final String ID = "ID";
		/**
		 * The Friends number.
		 */
		static final String FIRSTNAME = "FIRSTNAME";
		/**
		 * The Sent invitations number.
		 */
		static final String SURENAME = "SURENAME";

		/**
		 * The Gender.
		 */
		static final String GENDER = "GENDER";
		/**
		 * The Friends number.
		 */
		static final String SUBSCRIBEDNEWSLETTER = "SUBSCRIBEDNEWSLETTER";


		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

			User user = new User();
			user.setEmail(rs.getString(EMAIL));
			user.setId(rs.getInt(ID));
			user.setFirst_name(rs.getString(FIRSTNAME));
			user.setSureName(rs.getString(SURENAME));

			user.setGender(rs.getString(GENDER));
			user.setSubscribedNewsletter(rs.getBoolean(SUBSCRIBEDNEWSLETTER));

			return user;
		}
	}

}
