package com.tmm.nosql.mongodb.views;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.google.gson.Gson;

public class AjaxView extends AbstractView { 

    @Override
    protected void renderMergedOutputModel(Map map, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String jsonString = new Gson().toJson(map);
        response.setContentType( "text/plain; charset=UTF-8" );
        response.getOutputStream().write( jsonString.getBytes() );
    }
}