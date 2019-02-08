package com.rms.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringJdbc {
	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring_Jdbc.xml");

		HibernateDaoImpl hibDaoImpl = context.getBean("hibernateDaoImpl", HibernateDaoImpl.class);

		// create userDetails object
		UserDetails userDetails = new UserDetails();
		// not setting id hibernate will set
		userDetails.setUserName("Mohit");
		userDetails.setDescription("Coder");

		// create userDetails object
		UserDetails userDetails1 = new UserDetails();
		// not setting id hibernate will set
		userDetails1.setUserName("Rohit");
		userDetails1.setDescription("CA");

		// create vehicle object 1
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Bullet");

		vehicle.setUserDetailsList(Arrays.asList(userDetails, userDetails1));

		// create vehicle object 2
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleName("Dzire");

		vehicle1.setUserDetailsList(Arrays.asList(userDetails, userDetails1));

		// setting list of vehicles
		userDetails.setVehicleList(Arrays.asList(vehicle, vehicle1));
		userDetails1.setVehicleList(Arrays.asList(vehicle, vehicle1));

		List<Object> params = new ArrayList<Object>();

		params.add(vehicle);
		params.add(vehicle1);
		params.add(userDetails);
		params.add(userDetails1);
		hibDaoImpl.saveAll(params);

		System.out.println(hibDaoImpl.getVehicleCount());

		// getall vehicles
		List<Vehicle> vehicles = hibDaoImpl.getVehicles();
		for (Vehicle veh : vehicles) {
			System.out.println(veh);
		}
	}
}
