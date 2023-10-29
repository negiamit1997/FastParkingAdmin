package com.fastparking.testcases;

import org.testng.annotations.Test;

import com.fastparking.pageobject.LoginPage;
import com.fastparking.pageobject.PermitCards;
import com.fastparking.pageobject.Sidebar;
import com.fastparking.utilities.ProviderData;

import junit.framework.Assert;

public class TC_PermitCards extends BaseTest {
	String tabName = "Permit Cards";
	
	@Test(dataProvider = "addingPermitCardDataProvider", dataProviderClass = ProviderData.class)
	public void verifyAddPermitCard(String userMail, String ownermail, String vehicalNumber, String cardNumber) throws InterruptedException {
		log.info("Verify total registered user count Starting//////////");
		log.info("Logging in admin");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.doLogin(email, password);
		log.info("Login success");
		Sidebar ss = new Sidebar(driver);
		ss.getTab(tabName);	
		
		
		PermitCards permitCard = new PermitCards(driver);
		permitCard.assignPermitCard(userMail, ownermail, vehicalNumber, cardNumber);
		Assert.assertTrue(permitCard.SuccessToast.isDisplayed());
		permitCard.logout.click();
		
	}
	
}
