package com.finleap.user.repo;


import com.finleap.user.entity.User;

import java.util.List;
import java.util.concurrent.CompletionStage;

/**
 * The interface User repo.
 *
 * @author Kalidass Mahalingam
 */
public interface UserRepo {
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
	 * @param user the user
	 * @return the completion stage
	 */
	CompletionStage<User> save(User user);

}
