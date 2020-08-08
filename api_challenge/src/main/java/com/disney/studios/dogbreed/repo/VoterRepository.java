package com.disney.studios.dogbreed.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.studios.dogbreed.bean.Vote;

@Repository
public interface VoterRepository extends JpaRepository<Vote, Long> {

}
