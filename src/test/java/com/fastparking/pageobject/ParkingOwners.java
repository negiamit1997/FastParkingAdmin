package com.fastparking.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ParkingOwners {
	
	WebDriver driver;
	
	//constructor
	public ParkingOwners(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	//locators
	@FindBy(xpath="//button[text()='Add Parking Owner']")
	WebElement addParkingOwnerBtn;
	
	@FindBy(css="div[role='status']")
	WebElement loader;
	
	@FindBy(xpath="//h3")
	WebElement addOwnerpopupTitle;
	
	@FindBy(css="input[name='name']")
	WebElement ownerName;
	
	@FindBy(css="input[name='email']")
	WebElement ownerEmail;
	
	@FindBy(css=".css-1hwfws3")
	WebElement countryCodeDropdown;
	
	@FindBy(css="input[name='mobileNumber']")
	WebElement mobileNumber;
	
	@FindBy(css="input[name='password']")
	WebElement password;
	
	@FindBy(xpath="//button[text()='Save']")
	WebElement saveBtn;
	
	@FindBy(css=".css-yt9ioa-option")
	List<WebElement> countries;
	
	@FindBy(css=".toast-message")
	public WebElement SuccessToast;
	
	@FindBy(css=".toast-error")
	public WebElement failToast;
	
	@FindBy(css=".userlogout a")
	public WebElement logout;
	
	
	//methods
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public void addParkingOwner(String name, String email, String nameCountry, String mobile, String pass) throws InterruptedException{
		//waitForElementInvisibility(loader);
		addParkingOwnerBtn.click();
		waitForElementInvisibility(loader);
		//waitForElementVisibility(addOwnerpopupTitle);
		ownerName.sendKeys(name);
		ownerEmail.sendKeys(email);
		countryCodeDropdown.click();
		//Thread.sleep(2000);
		for(WebElement country: countries) {
			if(country.getText().contains(nameCountry)) {
				System.out.println(country.getText());
				//Thread.sleep(2000);;
				country.click();
				break;
			}
		}
		mobileNumber.sendKeys(mobile);
		password.sendKeys(pass);
		saveBtn.click();	
		
	}
	
	
	
	public void EditUser() {
		
	}
	
	

}
