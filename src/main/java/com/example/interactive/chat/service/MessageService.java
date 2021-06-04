package com.example.interactive.chat.service;

import java.util.List;

import com.example.interactive.chat.api.json.MsgObj;
import com.example.interactive.chat.exception.ApiServiceException;

/**
 *
 * @author Artur Korra
 */
public interface MessageService {

	List<MsgObj> getUnseenMessageByUserId(String userID) throws ApiServiceException;

	void markMsgAsSeen(String userID, String msgID) throws ApiServiceException;

	void sendMsg(String userID, MsgObj msgObj) throws ApiServiceException;

}
