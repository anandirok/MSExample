package com.disney.studios.dogbreed.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about Voter Details. ")
@Entity
@Table(name = "Vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ApiModelProperty(notes = "Client ID",value = "P1")
	@Column
	private String client;
	
	@ApiModelProperty(notes = "Picture ID",value = "P1")
	@Column
	private String pictureId;
	
	@ApiModelProperty(notes = "is voted or not",value = "true")
	@Column
	private boolean voteFlag;

	public Vote() {
		super();
	}

	public Vote(String client, String pictureId, boolean voteFlag) {
		super();
		this.client = client;
		this.pictureId = pictureId;
		this.voteFlag = voteFlag;
	}
	
	public Vote(long id,String client, String pictureId, boolean voteFlag) {
		super();
		this.id=id;
		this.client = client;
		this.pictureId = pictureId;
		this.voteFlag = voteFlag;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public boolean isVoteFlag() {
		return voteFlag;
	}

	public void setVoteFlag(boolean voteFlag) {
		this.voteFlag = voteFlag;
	}

	@Override
	public String toString() {
		return "Vote [ client=" + client + ", pictureId=" + pictureId + ", voteFlag=" + voteFlag
				+ "]";
	}

}
