package com.tmm.nosql.mongodb.domain;

public class Section {
	
	private String sectionTitle;
	
	private String sectionContent;

	public void setSectionContent(String sectionContent) {
		this.sectionContent = sectionContent;
	}

	public String getSectionContent() {
		return sectionContent;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

}
