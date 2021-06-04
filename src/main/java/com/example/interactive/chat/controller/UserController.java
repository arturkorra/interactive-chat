package com.example.interactive.chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interactive.chat.api.json.UserObj;
import com.example.interactive.chat.exception.ApiServiceException;
import com.example.interactive.chat.service.UserService;

/**
 *
 * @author Artur Korra
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<String> saveUser(@RequestBody @Valid UserObj userObj, HttpServletRequest request)
			throws ApiServiceException {
		logger.info("Save user: {}", userObj);
		String newUserID = userService.saveUser(userObj);
		logger.info("Save game END:   {}", userObj);
		return new ResponseEntity<String>(newUserID, HttpStatus.OK);
	}

}
