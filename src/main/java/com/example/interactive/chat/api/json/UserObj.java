package com.example.interactive.chat.api.json;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Artur Korra
 */
@ApiModel(description = "Json containing the data of User object")
public class UserObj {

	@NotNull(message = "FIRSTNAME IS NULL")
	@ApiModelProperty(value = "User firstname", required = true, allowEmptyValue = false)
	@Size(min = 2, max = 100, message = "The user firtsname must be between {min} and {max} characters long")
	private String firstName;

	@NotNull(message = "LASTNAME IS NULL")
	@ApiModelProperty(value = "User lastname", required = true, allowEmptyValue = false)
	@Size(min = 2, max = 100, message = "The user lastname must be between {min} and {max} characters long")
	private String lastName;

	@NotNull(message = "NICKNAME IS NULL")
	@ApiModelProperty(value = "User nickname", required = true, allowEmptyValue = false)
	@Size(min = 2, max = 100, message = "The user nickname must be between {min} and {max} characters long")
	private String nickName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
