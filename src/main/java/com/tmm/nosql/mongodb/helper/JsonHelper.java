package com.tmm.nosql.mongodb.helper;

import org.apache.commons.lang.WordUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tmm.nosql.mongodb.domain.Resume;
import com.tmm.nosql.mongodb.domain.ResumePage;
import com.tmm.nosql.mongodb.domain.Section;

public class JsonHelper {
	
	public static JsonObject buildJson(Resume r){
		JsonObject j = new JsonObject();
		j.addProperty("userName", WordUtils.capitalize(r.getId()));
		JsonArray pageArray = new JsonArray();
		for (ResumePage rp : r.getPages()){
			JsonObject page = new JsonObject();
			page.addProperty("pageName", WordUtils.capitalize(rp.getTitle()));
			JsonArray secArray = new JsonArray();
			for (Section s : rp.getSections()){
				JsonObject sec = new JsonObject();
				sec.addProperty("sectionTitle", WordUtils.capitalize(s.getSectionTitle()));
				sec.addProperty("sectionContent", s.getSectionContent());
				secArray.add(sec);
			}
			page.add("sections", secArray);
			pageArray.add(page);
		}
		j.add("pages", pageArray);
		return j;
	}

}
