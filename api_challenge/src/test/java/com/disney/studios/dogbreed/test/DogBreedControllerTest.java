package com.disney.studios.dogbreed.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.disney.studios.Application;
import com.disney.studios.dogbreed.bean.Dbreed;
import com.disney.studios.dogbreed.bean.DogBreedInfo;
import com.disney.studios.dogbreed.bean.RequestVoter;
import com.disney.studios.dogbreed.bean.VoteResult;
import com.disney.studios.dogbreed.controller.DogbreedController;
import com.disney.studios.dogbreed.exception.ResourceNotFoundException;
import com.disney.studios.dogbreed.service.DogInfoService;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
public class DogBreedControllerTest {

	@InjectMocks
	private DogbreedController dogbreedController;

	@Mock
	DogInfoService dogInfoService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getDogDetailsTest1() {

		List<Dbreed> lst = new ArrayList<Dbreed>();
		lst.add(new Dbreed(1, "2", "b2", 1, "abc", "pqr"));
		when(dogInfoService.getDogBreed()).thenReturn(lst);
		List<Dbreed> dblist = null;
		try {
			dblist = dogbreedController.getAllDogPictures();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1L, dblist.get(0).getId());
	}

	@Test
	public void getDogPicturesByBreedTest() {
		List<Dbreed> lst = new ArrayList<Dbreed>();
		lst.add(new Dbreed(1, "2", "b2", 1, "abc", "pqr"));
		when(dogInfoService.getDogByBreed(Mockito.anyString())).thenReturn(lst);
		List<Dbreed> dblist = null;
		try {
			dblist = dogbreedController.getDogPicturesByBreed("b2");
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1L, dblist.get(0).getId());
	}

	@Test
	public void votePictureTest() {
		VoteResult vr = new VoteResult(0, 1);
		when(dogInfoService.votePicture(Mockito.anyString(), Mockito.anyString(), Mockito.anyBoolean())).thenReturn(vr);
		VoteResult v = null;
		try {
			v = dogbreedController.votePicture(new RequestVoter("p1", "2", true));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(1L, v.getDislikes());
	}

	@Test
	public void sortedByVoteTest() {
		List<DogBreedInfo> lst = new ArrayList<DogBreedInfo>();
		lst.add(new DogBreedInfo("p1", "url", 1, "black"));
		when(dogInfoService.sortedByVote()).thenReturn(lst);
		List<DogBreedInfo> result = null;
		try {
			result = dogbreedController.sortedByVote();
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("p1", result.get(0).getBreed());
	}

}
