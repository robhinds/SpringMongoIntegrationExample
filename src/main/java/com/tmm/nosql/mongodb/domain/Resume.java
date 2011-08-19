package com.tmm.nosql.mongodb.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class Resume {
	
	@Id
	private String id;
	
	private List<ResumePage> pages = new ArrayList<ResumePage>();

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPages(List<ResumePage> pages) {
		this.pages = pages;
	}

	public List<ResumePage> getPages() {
		return pages;
	}
	
	public void addPage(ResumePage p){
		pages.add(p);
	}
	
	public void removePage(ResumePage p){
		pages.remove(p);
	}

}
