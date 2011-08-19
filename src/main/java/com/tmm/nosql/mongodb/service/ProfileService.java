package com.tmm.nosql.mongodb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tmm.nosql.mongodb.domain.Resume;
import com.tmm.nosql.mongodb.domain.ResumePage;
import com.tmm.nosql.mongodb.domain.Section;
import com.tmm.nosql.mongodb.repo.IResumeRepository;



@Repository("profileService")
@Transactional
public class ProfileService{

	@Autowired
	private IResumeRepository resumeRepo;

	public void setResumeRepo(IResumeRepository resumeRepo) {
		this.resumeRepo = resumeRepo;
	}

	/**
	 * Find a single Resume based on the username
	 * 
	 * @param userName
	 * @return
	 */
	public Resume get(String userName) {
		return resumeRepo.findOne(userName.toLowerCase());
	}

	/**
	 * Method to persist a resume to MongoDB
	 * 
	 * @param r
	 * @return
	 */
	public Boolean createResume(Resume r) {
		try {
			if (r.getId() == null || "".equals(r.getId().trim())){
				return false;
			}
			// Insert to db
			resumeRepo.save(r);
			return true;

		} catch (Exception e) {
			//log exceptions here!
			return false;
		}
	}

	/**
	 * Method to add a section to an existing resume
	 * 
	 * @param resumeId - name of resume
	 * @param pageId - page name
	 * @param sectionTitle - new section title
	 * @param sectionDetails - content of new section
	 */
	public void addSection(String resumeId, String pageId, String sectionTitle, String sectionDetails) {
		Resume r = get(resumeId);
		for (ResumePage rp : r.getPages()){
			if (rp.getTitle().equals(pageId)){
				Section s = new Section();
				s.setSectionTitle(sectionTitle);
				s.setSectionContent(sectionDetails);
				rp.addSection(s);
				break;
			}
		}
		
		resumeRepo.save(r);
	}
}