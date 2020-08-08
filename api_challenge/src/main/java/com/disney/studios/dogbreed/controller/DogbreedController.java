package com.disney.studios.dogbreed.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.disney.studios.dogbreed.bean.Dbreed;
import com.disney.studios.dogbreed.bean.DogBreedInfo;
import com.disney.studios.dogbreed.bean.RequestVoter;
import com.disney.studios.dogbreed.bean.VoteResult;
import com.disney.studios.dogbreed.exception.DuplicateDataException;
import com.disney.studios.dogbreed.exception.ResourceNotFoundException;
import com.disney.studios.dogbreed.repo.DbreedRepoImpl;
import com.disney.studios.dogbreed.service.DogInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Dog's Picture", description = "Disney Studios Dog Picture Gallery")
public class DogbreedController {

	@Autowired
	DogInfoService dogInfoService;

	@Autowired
	private DbreedRepoImpl dbreedRepoImpl;

	@PostConstruct
	public void init() {
		dbreedRepoImpl.loadData();
	}

	private static final Logger log = LoggerFactory.getLogger(DogbreedController.class);

	@ApiOperation(value = "Save new dog's picture in DB")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added new dog details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/dog/addNewDog", method = RequestMethod.POST)
	public String addDog(
			@ApiParam(value = "Dog Breed object store in database table", required = true) @RequestBody Dbreed dog)
			throws ResourceNotFoundException, DuplicateDataException {
		log.info("Inside in addDog controller : Saving Dog pictures in Database");
		if(dogInfoService.saveDogBreed(dog))
		{
			return "Successfully added new dog details";
		}
		
		return "Insertion failes";
	}

	@ApiOperation(value = "List all of the available dog pictures grouped by breed", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added new dod details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/allDogsDetails", method = RequestMethod.GET)
	public @ResponseBody List<Dbreed> getAllDogPictures() throws ResourceNotFoundException {
		log.info("Inside in getAllDogPictures controller : Fetaching all Dogs pictures from Database");
		List<Dbreed> l = dogInfoService.getDogBreed();
		if (l.isEmpty()) {
			throw new ResourceNotFoundException("Data is not Available!!");
		}
		return dogInfoService.getDogBreed();
	}

	@ApiOperation(value = "List all of the available dog pictures of a particular breed")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added new dod details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/dogBreed/{breed}", method = RequestMethod.GET)
	public List<Dbreed> getDogPicturesByBreed(
			@ApiParam(value = "Dog Breed Name ", required = true) @PathVariable String breed)
			throws ResourceNotFoundException {
		log.info("Inside in getDogPicturesByBreed controller : Fetaching particular breed pictures from Database");
		List<Dbreed> l = dogInfoService.getDogByBreed(breed);
		if (l.isEmpty()) {
			throw new ResourceNotFoundException("This Dog Breed is not Available!!");
		}
		return l;
	}

	@ApiOperation(value = "Get dog picture by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added new dog details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/dogid/{id}", method = RequestMethod.GET)
	public Dbreed getDogPicturesById(@ApiParam(value = "Dog Id ", required = true) @PathVariable long id)
			throws ResourceNotFoundException {
		log.info("Inside in getDogPicturesById controller : Fetaching particular breed pictures from Database");
		Optional<Dbreed> lid = dogInfoService.getDogById(id);
		if (!lid.isPresent()) {
			throw new ResourceNotFoundException("This Dog id is not Available!!");
		}
		return lid.get();
	}

	@ApiOperation(value = "Vote up and down a dog picture")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully client voted the dog's picture"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/dog/vote", method = RequestMethod.POST)
	public VoteResult votePicture(
			@ApiParam(value = "Voter details in object ", required = true) @RequestBody RequestVoter voter)
			throws ResourceNotFoundException {
		log.info("Inside in votePicture controller : Client Voted");
		return dogInfoService.votePicture(voter.getPictureName(), voter.getClientId(), voter.isFlag());
	}

	@ApiOperation(value = "The Dog Breed App expects the response to be sorted by the number of votes a picture has received.", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully fetch the sorted list of dogs details."),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/sortedByVote", method = RequestMethod.GET)
	public @ResponseBody List<DogBreedInfo> sortedByVote() throws ResourceNotFoundException {
		log.info("Inside in sortedByVote controller : Fetaching sorted by the number of votes a picture has received");
		List<DogBreedInfo> l = dogInfoService.sortedByVote();
		if (l.isEmpty()) {
			throw new ResourceNotFoundException("No data Available in Database!!");
		}
		return dogInfoService.sortedByVote();
	}

}
