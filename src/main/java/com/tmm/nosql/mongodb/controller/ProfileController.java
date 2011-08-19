package com.tmm.nosql.mongodb.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.google.gson.JsonObject;
import com.tmm.nosql.mongodb.domain.Resume;
import com.tmm.nosql.mongodb.domain.ResumePage;
import com.tmm.nosql.mongodb.helper.JsonHelper;
import com.tmm.nosql.mongodb.service.ProfileService;


public class ProfileController extends MultiActionController {
	
	@Autowired
	private ProfileService profileService;
	
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	
	/**
	 * Default handler to handle calls to view a resume. 
	 * If no resume exists for user then it automatically
	 * creates a blank resume to allow user to begin
	 * creating.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userName = request.getParameter("userName");
		Resume r = profileService.get(userName);
		if (r==null){
			r = new Resume();
			r.setId(userName.toLowerCase());
			ResumePage p = new ResumePage();
			p.setTitle("Introduction");
			ResumePage p2 = new ResumePage();
			p2.setTitle("Experience");
			ResumePage p3 = new ResumePage();
			p3.setTitle("Education");
			ResumePage p4 = new ResumePage();
			p4.setTitle("References");
			
			r.addPage(p);
			r.addPage(p2);
			r.addPage(p3);
			r.addPage(p4);
			
			profileService.createResume(r);
		}
		
		JsonObject res = JsonHelper.buildJson(r);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resume", res);
		return new ModelAndView("usereditpage", model);
	}

	
	/**
	 * Controller method to handle requests
	 * to add a new section to an existing resume.
	 * returns ajax data to calling form to refresh details.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView createSection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String sectionTitle = request.getParameter("sectionTitle");
		String sectionDetails = request.getParameter("sectionDetails");
		String resumeId = request.getParameter("resume");
		String pageId = request.getParameter("page");
		
		profileService.addSection(resumeId, pageId, sectionTitle, sectionDetails);
		
		JsonObject section = new JsonObject();
		section.addProperty("title", WordUtils.capitalize(sectionTitle));
		section.addProperty("details", StringUtils.capitalize(sectionDetails));
		model.put("section", section.toString());
		return new ModelAndView("ajax_useredit", model);
	}
}