package com.example.interactive.chat.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.interactive.chat.api.json.MsgObj;
import com.example.interactive.chat.exception.ApiServiceException;
import com.example.interactive.chat.exception.CustomErrors;
import com.example.interactive.chat.model.Message;
import com.example.interactive.chat.model.User;
import com.example.interactive.chat.repository.MessageRepository;
import com.example.interactive.chat.repository.UserRepository;
import com.example.interactive.chat.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<MsgObj> getUnseenMessageByUserId(String userID) throws ApiServiceException {
		Optional<User> user = userRepository.findById(userID);
		if (user.isEmpty()) {
			throw new ApiServiceException("User not found", CustomErrors.USER_NOT_FOUND.getCode());
		} else {
			List<Message> msgList = messageRepository.findByUserAndSeen(user, false);
			List<MsgObj> msgObjList = new ArrayList<MsgObj>();
			for (Message msg : msgList) {
				MsgObj msgObj = new MsgObj();
				msgObj.setUserID(msg.getUser().getId());
				msgObj.setContent(msg.getContent());
				msgObj.setSeen(msg.getSeen());
				msgObj.setMsgID(msg.getId());
				msgObjList.add(msgObj);
			}
			return msgObjList;
		}
	}

	@Override
	public void markMsgAsSeen(String userID, String msgID) throws ApiServiceException {
		Optional<User> user = userRepository.findById(userID);
		Optional<Message> msg = messageRepository.findById(msgID);
		if (user.isEmpty() || msg.isEmpty()) {
			throw new ApiServiceException("User or message not found!", CustomErrors.USER_MSG_NOT_FOUND.getCode());
		} else if (msg.get().getSeen()) {
			throw new ApiServiceException("Message already seen!", CustomErrors.MSG_SEEN.getCode());
		} else {
			msg.get().setSeen(true);
			messageRepository.save(msg.get());
		}
	}

	@Override
	public void sendMsg(String userID, MsgObj msgObj) throws ApiServiceException {
		Optional<User> user = userRepository.findById(userID);
		if (user.isEmpty()) {
			throw new ApiServiceException("User not found!", CustomErrors.USER_NOT_FOUND.getCode());
		} else {
			Message msg = new Message();
			msg.setContent(msgObj.getContent());
			msg.setSeen(false);
			msg.setUser(user.get());
			messageRepository.save(msg);
		}
	}
}