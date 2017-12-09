package com.geog.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Head;
import com.geog.Model.Region;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DAO {
	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<Country> loadCountries() throws Exception {
		ArrayList<Country> countries = new ArrayList<Country>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from country";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String co_code = myRs.getString("co_code");
			String co_name = myRs.getString("co_name");
			String co_details = myRs.getString("co_details");

			// create new country object
			Country country = new Country(co_code, co_name, co_details);

			countries.add(country);
		}	
		return countries;
	}


	public ArrayList<Region> loadRegions() throws Exception {
		ArrayList<Region> regions = new ArrayList<Region>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from region";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String co_code = myRs.getString("co_code");
			String reg_code = myRs.getString("reg_code");
			String reg_name = myRs.getString("reg_name");
			String reg_desc = myRs.getString("reg_desc");

			// create new country object
			Region region = new Region(co_code, reg_code, reg_name,reg_desc);

			regions.add(region);
		}
		return regions;
	}


	public ArrayList<City> loadCities() throws Exception {
		
		// TODO Auto-generated method stub
		ArrayList<City> cities = new ArrayList<City>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from city";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String cty_code = myRs.getString("cty_code");
			String co_code = myRs.getString("co_code");
			String reg_code = myRs.getString("reg_code");
			String cty_name = myRs.getString("cty_name");
			int population = myRs.getInt("population");
			boolean isCoastal = myRs.getBoolean("isCoastal");
			double areaKM = myRs.getDouble("areaKM");
		
			// create new country object
			City city = new City(cty_code, co_code, reg_code, cty_name, population,isCoastal,areaKM);

			cities.add(city);
		}
		
		return cities;
	}


	public ArrayList<Head> loadHeadOfStates() throws Exception {
		
		// TODO Auto-generated method stub
		ArrayList<Head> heads = new ArrayList<Head>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from headsOfState";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			String code = myRs.getString("code");
			String name = myRs.getString("name");
			String details = myRs.getString("details");
			
			// create new country object
			Head head = new Head(code, name, details);

			heads.add(head);
		}
		
		return heads;
	}


	// COUNTRY
	public void addCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into country values (?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCode());
		myStmt.setString(2, country.getName());
		myStmt.setString(3, country.getDetails());
		myStmt.execute();			
	}



	public void updateCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "update country set co_code = ?, co_name = ?, co_details = ? where co_code = ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCode());
		myStmt.setString(2, country.getName());
		myStmt.setString(3, country.getDetails());
		myStmt.setString(4, country.getCode());
		myStmt.execute();			
	}


	public void deleteCountry(Country country) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "delete from country where co_code like ?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, country.getCode());
		myStmt.execute();			
	}


	// REGION
	public void addRegion(Region region) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into region values (?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, region.getCode());
		myStmt.setString(2, region.getReg_code());
		myStmt.setString(3, region.getReg_name());
		myStmt.setString(4, region.getReg_desc());
		myStmt.execute();			
	}
	
	
	// CITY
	public void addCity(City city) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into city values (?, ?, ?, ?, ?, ?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, city.getCty_code());
		myStmt.setString(2, city.getCo_code());
		myStmt.setString(3, city.getReg_code());
		myStmt.setString(4, city.getCty_name());
		myStmt.setInt(5, city.getPopulation());
		myStmt.setString(6, String.valueOf(city.getCoastal()));
		myStmt.setDouble(7, city.getAreaKM());

		myStmt.execute();			
	}
	
	public City displayCity() throws Exception {
		Connection myConn = null;
		Statement myStmt = null;
		City cities = new City();
		//Connection myConn = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "SELECT*FROM CITY" + "INNER JOIN ON COUNTRY.CO_CODE = CITY.CO_CODE" + "INNER JOIN ON REGION.CO_CODE = CITY.REG_CODE";
		
		myStmt = myConn.createStatement();
		myRs = myStmt.executeQuery(sql);
		
		while(myRs.next()){
			String cty_code = myRs.getString("cty_code");
			String co_code = myRs.getString("co_code");
			String reg_code = myRs.getString("reg_code");
			String cty_name = myRs.getString("cty_name");
			String countryName = myRs.getString("co_name");
			String reg_name = myRs.getString("reg_name");			
			int population = myRs.getInt("population");
			boolean isCoastal = myRs.getBoolean("isCoastal");
			double areaKM = myRs.getDouble("areaKM");
			
			
			cities = new City( cty_code,  co_code,  reg_code,  cty_name,countryName,reg_name,  population,  isCoastal, areaKM);
		}
		return cities;
	}


	public void addHeadOfStates(Head head) {
		// TODO Auto-generated method stub
		
	}
}
