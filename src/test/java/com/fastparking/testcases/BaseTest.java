package com.fastparking.testcases;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fastparking.pageobject.LoginPage;
import com.fastparking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	ReadConfig readConfig = new ReadConfig();
	
	//config file values
	String URL = readConfig.getBaseURL();
	String browser = readConfig.getBrowser();
	String email = readConfig.getEmailAddress();
	String password = readConfig.getPassword();
	String IC_loginPageValidation = readConfig.getLoginPageValidation_IC();
	String deleteUserEmail = readConfig.getDeleteUser();
	String DataUpdateSuccessMessage = readConfig.getDataUpdateSuccessMessage();
	String blockUserEmail = readConfig.getBlockUser();
	String unblockUserEmail = readConfig.getUnblockUser();
	String searchUser = readConfig.getSearchUser();
	String addOwnerMessage = readConfig.getOwnerAddMsz();
	String parkingOwnerMail = readConfig.getParkingOwnerEmail();
	String BlockParkingOwnerMail = readConfig.getBlockParkingOwnerEmail();
	String UnBlockParkingOwnerMail = readConfig.getUnBlockParkingOwnerEmail();
	String parkingName = readConfig.getParkingName();
	String BookingOwnerName = readConfig.getBookingOwner();
	String BookingOwnerStatus = readConfig.getBookingOwnerStatus();
	String BookingID = readConfig.getBookingID();
	
	//Objects
	public static WebDriver driver;
	public static Logger log;
	
	@BeforeClass
	public void setup() {
		
		
		//browser invoke
		switch(browser.toLowerCase()){
		
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
			
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		
		default:
			driver = null;
			break;
		}
		
		//open URL
		driver.get(URL);
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//maximize browser
		driver.manage().window().maximize();
		
		//logs
		log = LogManager.getLogger("FastParkingAdmin");
	}
	
	
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	
	
	
	
	
	@AfterClass
	public void tearDown() {
		//driver.close();
		//driver.quit();
	}
	
}
