package com.geog.Controller;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Head;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped
public class HeadOfStateController {
	
	ArrayList<Head> heads;
	private DAO dao;


	public HeadOfStateController() {
		super();
		heads = new ArrayList<Head>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public HeadOfStateController(ArrayList<Head> heads) {
		super();
		this.heads = heads;
	}

	public ArrayList<Head> getCountries() {
		return heads;
	}

	public void setHeadOfStates(ArrayList<Head> heads) {
		this.heads = heads;
	}
	
	
	public void loadHeadOfStates() throws Exception {
		heads.clear();
		if (dao != null) {
			try {
				heads = dao.loadHeadOfStates();
				System.out.println(heads.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
//	public String addHeadOfStates(Head head) {
//		if (dao != null) {
//			try {
//				dao.addHeadOfStates(head);
//				return "index";
//			} catch (MySQLIntegrityConstraintViolationException e) {
//				FacesMessage message = new FacesMessage("Error: Product ID " + head.getHeadOfStates() + " already exists");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (CommunicationsException e) {
//				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (Exception e) {
//				FacesMessage message = new FacesMessage("Error while trying to insert Product " + head.getHeadOfStates());
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			}
//		}
//		return null;
//	}


}