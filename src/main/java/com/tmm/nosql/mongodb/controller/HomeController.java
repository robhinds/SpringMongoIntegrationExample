package com.tmm.nosql.mongodb.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;



public class HomeController extends MultiActionController implements InitializingBean
{

	public void afterPropertiesSet() throws Exception{}


	/**
	 * This method is called by the home page only - determines if anonymous 
	 * request and then either displays welcome info or login page
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView anonhome(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return new ModelAndView("userhomepage");
	}
}
