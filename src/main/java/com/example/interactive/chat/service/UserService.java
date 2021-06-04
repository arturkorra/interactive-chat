package com.example.interactive.chat.service;

import com.example.interactive.chat.api.json.UserObj;
import com.example.interactive.chat.exception.ApiServiceException;

public interface UserService {

	String saveUser(UserObj userObj) throws ApiServiceException;

}