package com.disney.studios.dogbreed.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about Voter details. ")
public class RequestVoter {
	
	@ApiModelProperty(notes = "Picture Name",value = "P1")
	private String pictureName;
	
	@ApiModelProperty(notes = "Client ID",value = "P1")
	private String clientId; 
	
	@ApiModelProperty(notes = "is voted or not",value = "true")
	private boolean flag;
	
	
	public RequestVoter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RequestVoter(String pictureName, String clientId, boolean flag) {
		super();
		this.pictureName = pictureName;
		this.clientId = clientId;
		this.flag = flag;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
