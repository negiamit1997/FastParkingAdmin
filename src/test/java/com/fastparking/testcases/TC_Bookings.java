package com.fastparking.testcases;

import org.testng.annotations.Test;

import com.fastparking.pageobject.Bookings;
import com.fastparking.pageobject.LoginPage;
import com.fastparking.pageobject.Sidebar;

import junit.framework.Assert;

public class TC_Bookings extends BaseTest{
	String tabName = "Bookings";
	
	@Test(enabled=false)
	public void verifBookingsCount() {
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		log.info("Counting the total number of users registered in app.");
		
		Bookings booking = new Bookings(driver);
		int totalBookings = booking.TotalBookings();
		log.info("Total bookings are: " + totalBookings);
	}
	
	@Test(enabled=false)
	public void verifyParticulerOwnerBooking() throws InterruptedException {
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		log.info("Counting the total number of users registered in app.");
		
		Bookings booking = new Bookings(driver);
		
		int bookingsCount = booking.ParticulerOwnerBooking(BookingOwnerName, BookingOwnerStatus);
		log.info(BookingOwnerName + " Booking count for status "+ BookingOwnerStatus + " is: "+ bookingsCount);
	}
	
	@Test()
	public void verifyBokingDetails() {
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		log.info("Counting the total number of users registered in app.");
		
		Bookings booking = new Bookings(driver);
		
		String BookingDetailsID = booking.searchAndViewBookingDetails(BookingID);
		
		Assert.assertEquals(BookingDetailsID , BookingID);
	}
	
}
