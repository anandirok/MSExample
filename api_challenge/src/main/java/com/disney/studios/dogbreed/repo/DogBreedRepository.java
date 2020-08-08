package com.disney.studios.dogbreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.studios.dogbreed.bean.Dbreed;

@Repository
public interface DogBreedRepository extends JpaRepository<Dbreed,Long>{

}
