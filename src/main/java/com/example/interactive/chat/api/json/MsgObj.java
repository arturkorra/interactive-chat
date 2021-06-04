package com.example.interactive.chat.api.json;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Artur Korra
 */
@ApiModel(description = "Json containing the data of Msg object")
public class MsgObj {

	@NotNull(message = "USERID IS NULL")
	@ApiModelProperty(value = "UserID", required = true, allowEmptyValue = false)
	@Size(min = 2, max = 100, message = "The userID must be between {min} and {max} characters long")
	private String userID;

	@ApiModelProperty(value = "UserID")
	@Size(min = 2, max = 100, message = "The userID must be between {min} and {max} characters long")
	private String msgID;

	@NotNull(message = "MSG IS NULL")
	@ApiModelProperty(value = "Msg content", required = true, allowEmptyValue = false)
	@Size(min = 2, max = 255, message = "The msg content must be between {min} and {max} characters long")
	private String content;

	@ApiModelProperty(value = "TRUE if msg is seen and FALSE if not")
	private boolean seen = false;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public String getMsgID() {
		return msgID;
	}

	public void setMsgID(String msgID) {
		this.msgID = msgID;
	}

}
