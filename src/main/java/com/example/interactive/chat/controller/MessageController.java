package com.example.interactive.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.interactive.chat.api.json.MsgObj;
import com.example.interactive.chat.exception.ApiServiceException;
import com.example.interactive.chat.service.MessageService;

import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
@RequestMapping("/message")
public class MessageController {

	public static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	@Autowired
	MessageService messageService;

	@GetMapping(value = { "/findMsg{userID}" })
	public ResponseEntity<List<MsgObj>> getUnseenMessageByUserId(
			@PathVariable(name = "userID", required = true) String userID) throws ApiServiceException {
		logger.info("Get MessageS by userID START: {}", userID);
		List<MsgObj> message = messageService.getUnseenMessageByUserId(userID);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	@PutMapping(value = { "/markMsg/{userID}" })
	public ResponseEntity<String> markMsgAsSeen(@PathVariable(name = "userID", required = true) String userID,
			@ApiParam(value = "Msg ID", required = true) @RequestParam(name = "msgID") String msgID)
			throws ApiServiceException {
		logger.info("Mark msg as seen by useID and msgID START: {}", userID + "," + msgID);
		messageService.markMsgAsSeen(userID, msgID);
		return new ResponseEntity<>("Message marked as seen!", HttpStatus.OK);
	}

	@PutMapping(value = { "/sendMsg" })
	public ResponseEntity<String> sendMsg(
			@ApiParam(value = "User ID", required = true) @RequestParam(name = "userID") String userID,
			@RequestBody @Valid MsgObj msgObj) throws ApiServiceException {
		logger.info("Send msg by useID START: {}", userID + "," + msgObj);
		messageService.sendMsg(userID, msgObj);
		return new ResponseEntity<>("Message sent!", HttpStatus.OK);
	}
}
