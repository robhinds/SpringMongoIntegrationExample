package com.tmm.nosql.mongodb.domain;

import java.util.ArrayList;
import java.util.List;

public class ResumePage {
	
	private String title;
	
	private List<Section> sections = new ArrayList<Section>();

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Section> getSections() {
		return sections;
	}
	
	public void addSection(Section s){
		sections.add(s);
	}
	
	public void removeSection(Section s){
		sections.remove(s);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
