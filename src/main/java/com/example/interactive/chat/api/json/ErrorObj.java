package com.example.interactive.chat.api.json;

import java.io.Serializable;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Artur Korra
 */
@ApiModel(description = "Output Json containing error informations")
public class ErrorObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2935879222835344829L;

	public ErrorObj(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public ErrorObj(Integer code, String message, Map<String, Object> info) {
		this.code = code;
		this.message = message;
		this.info = info;
	}

	@ApiModelProperty(value = "Error code")
	private Integer code;
	@ApiModelProperty(value = "Error message")
	private String message;
	@ApiModelProperty(value = "Additional Info")
	private Map<String, Object> info;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{ ");
		if (code != null)
			builder.append("\"code\": ").append(code).append(", ");
		if (message != null)
			builder.append("\"message\": \"").append(message).append("\", ");
		if (info != null)
			builder.append("\"info\": \"").append(info).append("\", ");
		builder.setLength(builder.length() - 2);
		builder.append(" }");
		return builder.toString();
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}

}
