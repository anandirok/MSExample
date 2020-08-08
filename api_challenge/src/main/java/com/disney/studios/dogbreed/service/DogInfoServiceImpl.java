package com.disney.studios.dogbreed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.studios.dogbreed.bean.Dbreed;
import com.disney.studios.dogbreed.bean.DogBreedInfo;
import com.disney.studios.dogbreed.bean.VoteResult;
import com.disney.studios.dogbreed.exception.DuplicateDataException;
import com.disney.studios.dogbreed.exception.ResourceNotFoundException;
import com.disney.studios.dogbreed.repo.DbreedRepoImpl;

@Service
public class DogInfoServiceImpl implements DogInfoService {

	@Autowired
	private DbreedRepoImpl dbreedRepoImpl;
	
	@Override
	public boolean saveDogBreed(Dbreed dbreed)  throws ResourceNotFoundException,DuplicateDataException{
		return dbreedRepoImpl.saveDogBreed(dbreed);
	}

	@Override
	public List<Dbreed> getDogBreed() {
		return dbreedRepoImpl.getDogBreed();
	}

	@Override
	public List<Dbreed> getDogByBreed(String breed) {
		return dbreedRepoImpl.getDogByBreed(breed);
	}

	@Override
	public VoteResult getPictureVotes(String pictureId) {
		return dbreedRepoImpl.getPictureVotes(pictureId);
	}

	@Override
	public VoteResult votePicture(String pictureId, String clientId, boolean flag) {
		return dbreedRepoImpl.votePicture(pictureId, clientId, flag);
	}

	@Override
	public List<DogBreedInfo> sortedByVote() {
		return dbreedRepoImpl.sortedByVote();
	}

	@Override
	public boolean isAlreadyExists(Dbreed dbreed) {
		return dbreedRepoImpl.isAlreadyExists(dbreed);
	}

	@Override
	public Optional<Dbreed> getDogById(long id) {
		return dbreedRepoImpl.getDogById(id);
	}

}
