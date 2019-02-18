package com.finleap.user.service.impl;


import com.finleap.user.entity.User;
import com.finleap.user.repo.UserRepo;
import com.finleap.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletionStage;


/**
 * The type User service.
 *
 * @author Kalidass Mahalingam
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

	/**
	 * The Home repo.
	 */
	@Autowired
	UserRepo homeRepo;
	
	@Override
	public CompletionStage<User> getUser(int id) {
		return homeRepo.getUser(id);
	}

	@Override
	public CompletionStage<List<User>> getAllUser() {
		return homeRepo.getAllUser();
	}

	@Override
	public CompletionStage<User> save(User user) {
		return homeRepo.save(user);
	}

}
