package com.tmm.nosql.mongodb.repo;

import org.springframework.data.document.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.mongodb.domain.Resume;


@Transactional
public interface IResumeRepository extends MongoRepository<Resume, String>{

}
