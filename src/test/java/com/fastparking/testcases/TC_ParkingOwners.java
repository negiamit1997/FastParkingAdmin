package com.fastparking.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fastparking.pageobject.LoginPage;
import com.fastparking.pageobject.ParkingOwners;
import com.fastparking.pageobject.Sidebar;
import com.fastparking.utilities.ProviderData;

import junit.framework.Assert;

public class TC_ParkingOwners extends BaseTest{
	String tabName = "Parking Owners";
	DataFormatter formatter = new DataFormatter();
	
	
	@Test(dataProvider = "parkingOwnerDataProvider", dataProviderClass = ProviderData.class)
	public void verifyAddParkingOwner(String name, String mail, String country, String mobile, String pass) throws InterruptedException {
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		
		
		ParkingOwners parkingOwners = new ParkingOwners(driver);
		parkingOwners.addParkingOwner(name, mail, country, mobile, pass);
		Thread.sleep(1000);
		Assert.assertTrue(parkingOwners.SuccessToast.isDisplayed());
		Assert.assertEquals(addOwnerMessage,parkingOwners.SuccessToast.getText());
		parkingOwners.logout.click();
		
	}
	


}
