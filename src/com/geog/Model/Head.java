package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Head {
//	Instance variables
	private String code;
	private String name;
	private String details;
	
	public Head() {
		
	}

	public Head(String code, String name, String details) {
		super();
		this.code = code;
		this.name = name;
		this.details = details;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

//	public String getHeadOfStates() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
} // class Head