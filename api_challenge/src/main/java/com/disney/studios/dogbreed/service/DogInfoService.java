package com.disney.studios.dogbreed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.disney.studios.dogbreed.bean.Dbreed;
import com.disney.studios.dogbreed.bean.DogBreedInfo;
import com.disney.studios.dogbreed.bean.VoteResult;
import com.disney.studios.dogbreed.exception.DuplicateDataException;
import com.disney.studios.dogbreed.exception.ResourceNotFoundException;

@Service
public interface DogInfoService {
	
	public boolean saveDogBreed(Dbreed dbreed) throws ResourceNotFoundException,DuplicateDataException;
	public List<Dbreed> getDogBreed();
	public List<Dbreed> getDogByBreed(String breed);
	public VoteResult getPictureVotes(String pictureId) ;
	public VoteResult votePicture(String pictureId, String clientId, boolean flag) ;
	public List<DogBreedInfo> sortedByVote();
	public boolean isAlreadyExists(Dbreed dbreed);
	public Optional<Dbreed> getDogById(long id);
	
	

}
