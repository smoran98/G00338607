package com.geog.Controller;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Country;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped
public class CountryController {
	
	ArrayList<Country> countries;
	private DAO dao;


	public CountryController() {
		super();
		countries = new ArrayList<Country>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CountryController(ArrayList<Country> countries) {
		super();
		this.countries = countries;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}
	
	
	public void loadCountries() throws Exception {
		countries.clear();
		if (dao != null) {
			try {
				countries = dao.loadCountries();
				System.out.println(countries.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// METHODS
	public String addCountry(Country country) throws MySQLIntegrityConstraintViolationException, CommunicationsException {
		if (dao != null) {
			try {
				dao.addCountry(country);
				return "index";
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " +  country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String deleteCountry(Country country) {
		if (dao != null) {
			try {
				dao.deleteCountry(country);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Country ID " + country.getCode() + " not found");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to delete Country " + country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
	public String updateCountry(Country country) throws MySQLIntegrityConstraintViolationException, CommunicationsException {
		if (dao != null) {
			try {
				System.out.println(country);
				dao.updateCountry(country);
				return "index";
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to update Country " + country.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}

}
