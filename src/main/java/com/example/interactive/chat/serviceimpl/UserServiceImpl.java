package com.example.interactive.chat.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.interactive.chat.api.json.UserObj;
import com.example.interactive.chat.exception.ApiServiceException;
import com.example.interactive.chat.exception.CustomErrors;
import com.example.interactive.chat.model.User;
import com.example.interactive.chat.repository.UserRepository;
import com.example.interactive.chat.service.UserService;

/**
 *
 * @author Artur Korra
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(rollbackFor = { ApiServiceException.class, Exception.class })
	public String saveUser(UserObj userObj) throws ApiServiceException {
		User user = new User();
		if (userRepository.findBynickName(userObj.getNickName()) != null) {
			throw new ApiServiceException("User exist", CustomErrors.NICKNAME_ISUSED.getCode());
		} else {
			user.setFirstName(userObj.getFirstName());
			user.setLastName(userObj.getLastName());
			user.setNickName(userObj.getNickName());
			User newUser = userRepository.save(user);
			return newUser.getId();
		}
	}
}
