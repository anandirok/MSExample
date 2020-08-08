package com.disney.studios.dogbreed.bean;

public class VoteResult {
	
	private long likes;
	private long dislikes;
	private String comment;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public VoteResult() {}
	public VoteResult(long likes, long dislikes) {
		super();
		this.likes = likes;
		this.dislikes = dislikes;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getDislikes() {
		return dislikes;
	}
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	
	

}
