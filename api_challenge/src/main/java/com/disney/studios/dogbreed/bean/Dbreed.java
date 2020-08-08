package com.disney.studios.dogbreed.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Dog Breed Picture. ")
@Entity
@Table(name = "Dbreed")
@JsonInclude(Include.NON_NULL)
public class Dbreed {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
		
	@ApiModelProperty(notes = "Picture Name",value = "P1")
	@Column
	private String pictureName;
	
	@ApiModelProperty(notes = "breed Name",value = "B1")
	@Column
	private String breed;
	
	@ApiModelProperty(notes = "No of Vote",value = "0")
	@Column
	private int noOfVote;
	
	@ApiModelProperty(notes = "Picture URL",value = "home/url/p1")
	@Column
	private String pictureUrl;
	
	@ApiModelProperty(notes = "Description Of Picture",value = "black")
	@Column
	private String descriptionOfPicture;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getNoOfVote() {
		return noOfVote;
	}

	public void setNoOfVote(int noOfVote) {
		this.noOfVote = noOfVote;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getDescriptionOfPicture() {
		return descriptionOfPicture;
	}

	public void setDescriptionOfPicture(String descriptionOfPicture) {
		this.descriptionOfPicture = descriptionOfPicture;
	}

	public Dbreed(long id, String pictureName, String breed, int noOfVote, String pictureUrl,
			String descriptionOfPicture) {
		super();
		this.id = id;
		this.pictureName = pictureName;
		this.breed = breed;
		this.noOfVote = noOfVote;
		this.pictureUrl = pictureUrl;
		this.descriptionOfPicture = descriptionOfPicture;
	}

	public Dbreed() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Dbreed(String pictureName, String breed, int noOfVote, String pictureUrl,
			String descriptionOfPicture) {
		super();
		this.pictureName = pictureName;
		this.breed = breed;
		this.noOfVote = noOfVote;
		this.pictureUrl = pictureUrl;
		this.descriptionOfPicture = descriptionOfPicture;
	}
	
}

