package com.finleap.user.service;


import com.finleap.user.entity.User;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * The interface User service.
 *
 * @author Kalidass Mahalingam
 */
public interface UserService {

	/**
	 * Gets user.
	 *
	 * @param id the id
	 * @return the user
	 */
	CompletionStage<User> getUser(int id);

	/**
	 * Gets all user.
	 *
	 * @return the all user
	 */
	CompletionStage<List<User>> getAllUser();

	/**
	 * Save completion stage.
	 *
	 * @param template the template
	 * @return the completion stage
	 */
	CompletionStage<User> save(User template);


}
