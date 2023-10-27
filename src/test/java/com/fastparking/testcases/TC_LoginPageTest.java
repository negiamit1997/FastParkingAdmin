package com.fastparking.testcases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fastparking.pageobject.LoginPage;

import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_LoginPageTest extends BaseTest{

	
	@Test()
	public void verifyLoginInvalidCredentials() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		log.info("Verifyin the invalid credentials validation message for login page");
		log.info("Login page open");
		
		//checking login page title
		String loginPageTtitle = loginPage.getformTitle();
		Assert.assertEquals(loginPageTtitle, "Log In to Admin Panel");
		log.info("Verified login page title");
		
		//checking logo of the admin panel
		String imgPath=System.getProperty("user.dir") + "\\Images\\Login\\logo.png";
		BufferedImage expectedLogo = ImageIO.read(new File(imgPath));
		
		Screenshot logoImage = loginPage.getLogo();
		BufferedImage actualLogo = logoImage.getImage();
		
		ImageDiffer imageDifference = new ImageDiffer();
		ImageDiff verifyImage = imageDifference.makeDiff(expectedLogo, actualLogo);
		
		if(verifyImage.hasDiff()==true) {
			log.info("Images are same");
		}else {
			log.info("Images are not same");
			Assert.assertTrue(false);
		}
		
		
		//doing login
		loginPage.doLogin("test@yopmail.com", "123");
		waitForElementVisibility(loginPage.invalidCredentialsValidationLocator);
		
		String validationMessage = loginPage.getInvalidCredentialsValidationText();
		Assert.assertEquals(validationMessage, IC_loginPageValidation);
		log.info("Verified login page invalid credentials validation message");
		
		//clear data from input fields
		loginPage.clearInputFieldData();
		log.info("Cleared invalid data from email and password input fields");
		driver.navigate().refresh();
	}
	
	
	@Test
	public void verifyLoginValidCredentials() throws InterruptedException, IOException {
		LoginPage loginPage = new LoginPage(driver);
		log.info("Verifying login through valid credentials");
		log.info("Login page open");
		
		//checking login page title
		String loginPageTtitle = loginPage.getformTitle();
		Assert.assertEquals(loginPageTtitle, "Log In to Admin Panel");
		log.info("Verified login page title");
		
		//checking logo of the admin panel
		String imgPath=System.getProperty("user.dir") + "\\Images\\Login\\logo.png";
		BufferedImage expectedLogo = ImageIO.read(new File(imgPath));
				
		Screenshot logoImage = loginPage.getLogo();
		BufferedImage actualLogo = logoImage.getImage();
				
		ImageDiffer imageDifference = new ImageDiffer();
		ImageDiff verifyImage = imageDifference.makeDiff(expectedLogo, actualLogo);
				
		if(verifyImage.hasDiff()==true) {
			log.info("Images are same");
		}else {
				log.info("Images are not same");
				Assert.assertTrue(false);
		}
			
		//doing login
		loginPage.doLogin(email, password);
		String adminText = loginPage.getDashboardAdminText();
		Assert.assertEquals(adminText, "admin");
		log.info("Login success");
	}
	
}
