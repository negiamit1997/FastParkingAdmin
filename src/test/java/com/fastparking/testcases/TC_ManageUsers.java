package com.fastparking.testcases;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.fastparking.pageobject.LoginPage;
import com.fastparking.pageobject.ManageUsers;
import com.fastparking.pageobject.Sidebar;

import junit.framework.Assert;

public class TC_ManageUsers extends BaseTest{
	
	String tabName = "Manage Users";
	
	@Test(enabled=false)
	public void verifyTotalUsersCount() throws InterruptedException{
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		log.info("Counting the total number of users registered in app.");
		ManageUsers manageUsers = new ManageUsers(driver);
		waitForElementClickable(manageUsers.tableLastrow);
		int totalUsersCount = manageUsers.TotalUsers();
		log.info("Total number of users registered in app: " + totalUsersCount);
		log.info("Verify total registered user count Ending//////////");
	}
	
	@Test(enabled=false)
	public void verifyDeleteUser() throws InterruptedException {
		log.info("Verify delete user feature////////////");
		log.info("Loggining into admin panel");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		
		ManageUsers manageUsers = new ManageUsers(driver);
		waitForElementClickable(manageUsers.tableLastrow);
		log.info("finding user to delete");
		manageUsers.searchAndDeleteUser(deleteUserEmail);
		Assert.assertTrue(manageUsers.SuccessToast.isDisplayed());
		Assert.assertEquals(manageUsers.SuccessToast.getText(), DataUpdateSuccessMessage);
		log.info("user deleted success////////////////////");
		
	}
	
	@Test(enabled=false)
	public void verifyBlockUser() throws InterruptedException {
		log.info("Verify block user feature////////////");
		log.info("Loggining into admin panel");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		
		ManageUsers manageUsers = new ManageUsers(driver);
		waitForElementClickable(manageUsers.tableLastrow);
		log.info("finding user to block");
		manageUsers.SearchAndBlockUser(blockUserEmail);
		Assert.assertTrue(manageUsers.SuccessToast.isDisplayed());
		Assert.assertEquals(manageUsers.SuccessToast.getText(), DataUpdateSuccessMessage);
		log.info("user blocked success////////////////////");
	}
	
	@Test(enabled=false)
	public void verifyUnblockUser() throws InterruptedException {
		log.info("Verify unblock user feature////////////");
		log.info("Loggining into admin panel");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		
		ManageUsers manageUsers = new ManageUsers(driver);
		waitForElementClickable(manageUsers.tableLastrow);
		log.info("finding user to unblock");
		manageUsers.SearchAndunBlockUser(unblockUserEmail);
		Assert.assertTrue(manageUsers.SuccessToast.isDisplayed());
		Assert.assertEquals(manageUsers.SuccessToast.getText(), DataUpdateSuccessMessage);
		log.info("user unblocked success////////////////////");
	}
	
	@Test()
	public void verifySearchFeature() throws AWTException {
		log.info("Verify search user feature////////////");
		log.info("Loggining into admin panel");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		log.info("Navigated to manage users");
		
		ManageUsers manageUsers = new ManageUsers(driver);
		String userEmail = manageUsers.searchUser(searchUser);
		Assert.assertEquals(userEmail,searchUser);
	}
	
	
	

}
