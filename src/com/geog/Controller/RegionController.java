package com.geog.Controller;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Country;
import com.geog.Model.Region;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped
public class RegionController {
	
	ArrayList<Region> regions;
	private DAO dao;


	public RegionController() {
		super();
		regions = new ArrayList<Region>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public RegionController(ArrayList<Region> regions) {
		super();
		this.regions = regions;
	}

	public ArrayList<Region> getRegion() {
		return regions;
	}

	public void setRegion(ArrayList<Region> countries) {
		this.regions = regions;
	}
	
	
	public void loadRegions() throws Exception {
		regions.clear();
		if (dao != null) {
			try {
				regions = dao.loadRegions();
				System.out.println(regions.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// REGION
	public String addRegion(Region region) throws MySQLIntegrityConstraintViolationException, CommunicationsException {
		if (dao != null) {
			try {
				dao.addRegion(region);
				return "index";
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Country " +  region.getCode());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}
	
//	public String deleteCountry(Country country) {
//		if (dao != null) {
//			try {
//				dao.deleteCountry(country);
//				return "index";
//			} catch (MySQLIntegrityConstraintViolationException e) {
//				FacesMessage message = new FacesMessage("Error: Country ID " + country.getCode() + " not found");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (CommunicationsException e) {
//				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (Exception e) {
//				FacesMessage message = new FacesMessage("Error while trying to delete Country " + country.getCode());
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			}
//		}
//		return null;
//	}
	



//	public String addProduct(Country country) {
//		if (dao != null) {
//			try {
//				dao.addProduct(product);
//				return "index";
//			} catch (MySQLIntegrityConstraintViolationException e) {
//				FacesMessage message = new FacesMessage("Error: Product ID " + product.getProductID() + " already exists");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (CommunicationsException e) {
//				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			} catch (Exception e) {
//				FacesMessage message = new FacesMessage("Error while trying to insert Product " + product.getProductID());
//				FacesContext.getCurrentInstance().addMessage(null, message);
//				return null;
//			}
//		}
//		return null;
//	}


}