package com.disney.studios.dogbreed.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Dog Breed Picture. ")
public class DogBreedInfo {
	
	@ApiModelProperty(notes = "Dog Breed Name",value = "B1")
	private String breed;
	
	@ApiModelProperty(notes = "Picture Url",value = "home/url/h1")
	private String pictureUrl;
	
	@ApiModelProperty(notes = "number of vote",value = "0")
	private long noOfVote;
	
	@ApiModelProperty(notes = "Description of picture",value = "black dog")
	private String description;
	
	
	public DogBreedInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public DogBreedInfo(String breed, String pictureUrl, long noOfVote, String description) {
		super();
		this.breed = breed;
		this.pictureUrl = pictureUrl;
		this.noOfVote = noOfVote;
		this.description = description;
	}


	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public long getNoOfVote() {
		return noOfVote;
	}
	public void setNoOfVote(long noOfVote) {
		this.noOfVote = noOfVote;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
