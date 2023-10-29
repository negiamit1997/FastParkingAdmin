package com.fastparking.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fastparking.pageobject.LoginPage;
import com.fastparking.pageobject.Parkings;
import com.fastparking.pageobject.Sidebar;
import com.fastparking.utilities.ParkingData;
import com.fastparking.utilities.ProviderData;

import junit.framework.Assert;

public class TC_Parking extends BaseTest{

	@Test(dataProvider="parkingData", dataProviderClass = ParkingData.class, enabled=false)
	public void verifyAddParking(String picName,String nameParking, String parkShortName, String parkNumber, String zoneName, String openHrs, String openMins, String closeHrs, String closeMins, String nameOwner, String address, String parkingType, String buildingFloor, String slot) throws IOException, InterruptedException {
		String tabName = "Parking";
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);
		
		
		Parkings parking = new Parkings(driver);
		parking.AddParking(picName, nameParking, parkShortName, parkNumber, zoneName, openHrs, openMins, closeHrs, closeMins, nameOwner, address, parkingType, buildingFloor, slot);
		//parking.AddParking("TIME");
		Assert.assertTrue(parking.SuccessToast.isDisplayed());
	
			}
	
	@Test(dataProvider="editParkingData", dataProviderClass = ParkingData.class)
	public void verifyEditParking(String editPark, String picName,String nameParking, String parkShortName, String parkNumber, String zoneName, String openHrs, String openMins, String closeHrs, String closeMins, String nameOwner, String address, String parkingType, String buildingFloor, String slot) throws IOException, InterruptedException {
		String tabName = "Parking";
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);
		
		
		Parkings parking = new Parkings(driver);
		parking.editparking(editPark,picName, nameParking, parkShortName, parkNumber, zoneName, openHrs, openMins, closeHrs, closeMins, nameOwner, address, parkingType, buildingFloor, slot);
		
		Assert.assertTrue(parking.SuccessToast.isDisplayed());
			}
	
	
	@Test(enabled=false)
	public void verifyParkingExists() throws InterruptedException {
		String tabName = "Parking";
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);
		
		
		Parkings parking = new Parkings(driver);
		String parkName = parking.FindParking(parkingName);
		Assert.assertEquals(parkName, parkingName);
	}
	
	@Test(enabled=false)
	public void verifyDeleteParking() throws InterruptedException {
		String tabName = "Parking";
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);
		
		
		Parkings parking = new Parkings(driver);
		parking.searchAndDeleteParking(parkingName);
		Assert.assertTrue(parking.SuccessToast.isDisplayed());
	}
	
	
	
}
