package com.disney.studios.dogbreed.repo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.disney.studios.PetLoader;
import com.disney.studios.dogbreed.bean.Dbreed;
import com.disney.studios.dogbreed.bean.DogBreedInfo;
import com.disney.studios.dogbreed.bean.Vote;
import com.disney.studios.dogbreed.bean.VoteResult;
import com.disney.studios.dogbreed.exception.DuplicateDataException;
import com.disney.studios.dogbreed.exception.ResourceNotFoundException;

@Service
public class DbreedRepoImpl {

	@Autowired
	private DogBreedRepository dogBreedRepository;

	@Autowired
	private PetLoader petLoader;

	@Autowired
	private VoterRepository voterRepository;

	@Autowired
	DataSource dataSource;

	public void loadData() {
		try {
			File fileLab = petLoader.getLabradors().getFile();
			dogBreedRepository.saveAll(getDogList(fileLab));

			File filePug = petLoader.getPugs().getFile();
			dogBreedRepository.saveAll(getDogList(filePug));

			File fileRetrievers = petLoader.getRetrievers().getFile();
			dogBreedRepository.saveAll(getDogList(fileRetrievers));

			File fileYorkies = petLoader.getYorkies().getFile();
			dogBreedRepository.saveAll(getDogList(fileYorkies));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean saveDogBreed(Dbreed dbreed)  throws ResourceNotFoundException,DuplicateDataException{

		if(isAlreadyExists(dbreed))
		{
			Dbreed d = dogBreedRepository.save(dbreed);
			if (StringUtils.isEmpty(d)) {
				throw new ResourceNotFoundException("Insertion failed");
			}
		}
		else
		{
			throw new DuplicateDataException("Already exists this details in db");
		}
		return true;
	}

	public String getFile(String str) {
		String[] p1 = str.split("/");

		return p1[3].trim();
	}

	public List<Dbreed> getDogList(File file) {
		String labradors = null;
		try {
			labradors = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> lst = Arrays.asList(labradors.split("\n"));
		List<Dbreed> labList = lst.stream()
				.map(p -> new Dbreed(getBreedName(getFile(p).trim()), getBreedName(file.getName().toString()), 0, p.trim(), file.getName()))
				.collect(Collectors.toList());

		return labList;

	}

	private String getBreedName(String name) {
		return name.substring(0, name.lastIndexOf(".")).trim();
	}

	public boolean isAlreadyExists(Dbreed dbreed) {
		return dogBreedRepository.findAll().stream().filter(
				p -> p.getBreed().equals(dbreed.getBreed()) && p.getPictureName().equals(dbreed.getPictureName()))
				.collect(Collectors.toList()).isEmpty();
	}

	public List<Dbreed> getDogBreed() {
		List<Dbreed> l = dogBreedRepository.findAll();
		return l;
	}

	public List<Dbreed> getDogByBreed(String breed) {
		return dogBreedRepository.findAll().stream().filter(p -> p.getBreed().equalsIgnoreCase(breed.trim())).distinct()
				.collect(Collectors.toList());
	}

	public VoteResult getPictureVotes(String pictureId) {
		return new VoteResult(
				voterRepository.findAll().stream()
						.filter(v -> v.isVoteFlag() == true && v.getPictureId().equalsIgnoreCase(pictureId)).count(),
				voterRepository.findAll().stream()
						.filter(v -> v.isVoteFlag() == false && v.getPictureId().equalsIgnoreCase(pictureId)).count());
	}

	public VoteResult votePicture(String pictureName, String clientId, boolean flag) {

		if (isAllow(pictureName, clientId)) {
			voterRepository.save(new Vote(clientId, pictureName, flag));
			VoteResult vr = getPictureVotes(pictureName);
			vr.setComment("Voted");
			return vr;
		}
		VoteResult vr1 = getPictureVotes(pictureName);
		vr1.setComment("Already Voted");

		return vr1;

	}

	private boolean isAllow(String pictureId, String clientId) {
		long l = voterRepository.findAll().stream()
				.filter(v -> v.getClient().equalsIgnoreCase(clientId) && v.getPictureId().equalsIgnoreCase(pictureId))
				.count();
		if (l > 0) {
			return false;
		}
		return true;
	}

	public List<DogBreedInfo> sortedByVote() {

		List<DogBreedInfo> info = new ArrayList<DogBreedInfo>();
		for (Dbreed p : getDogBreed()) {
			long no = voterRepository.findAll().stream().distinct()
					.filter(v -> v.getPictureId().equalsIgnoreCase(p.getPictureName())).count();
			if (no > 0) {
				info.add(new DogBreedInfo(p.getBreed(), p.getPictureUrl(), no, p.getDescriptionOfPicture()));

			}
		}
		return info.stream().distinct().sorted((a, b) -> a.getNoOfVote() > b.getNoOfVote() ? 1 : -1)
				.collect(Collectors.toList());
	}

	public Optional<Dbreed> getDogById(long id) {

		return dogBreedRepository.findById(id);
	}

}
