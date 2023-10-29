package com.fastparking.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(id="search-bar-0")
	WebElement searchField;
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> userEntry;
	
	@FindBy(xpath="//button[text()='Update']")
	WebElement updateBtn;
	
	@FindBy(linkText="Profile")
	public WebElement profileTitle;
	
	@FindBy(xpath="//div[@class='status_profile'][2]/p")
	WebElement UserStatus;
	
	@FindBy(xpath="//button[text()='Block']")
	WebElement blockBtn;
	
	@FindBy(xpath="//button[text()='Active']")
	WebElement unblockBtn;
	
	@FindBy(css=".infoimg")
	WebElement confirmation;
	
	@FindBy(xpath="//button[text()='Yes, delete it!']")
	WebElement confrmYes;

	
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
	
	
	public String searchFeature(String email) {
		searchField.sendKeys(email, Keys.ENTER);
		waitForElementInvisibility(loader);
		String mail = "";
		for(WebElement user: userEntry) {
			mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText();
		}
		return mail;
	}
	
	public void editParkingOwner(String searchEmail, String name, String email, String nameCountry, String mobile) {
		searchField.sendKeys(searchEmail, Keys.ENTER);
		waitForElementInvisibility(loader);
		for(WebElement user: userEntry) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText();
			if(mail.equalsIgnoreCase(searchEmail)) {
				WebElement editBtn = user.findElement(By.cssSelector("td:last-child a:first-child"));
				Actions ac = new Actions(driver);
				ac.moveToElement(editBtn).doubleClick().perform();
			}
			ownerName.clear();
			ownerName.sendKeys(name);
			ownerEmail.clear();
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
			mobileNumber.clear();;
			mobileNumber.sendKeys(mobile);
			updateBtn.click();
			
		}
	}
	
	
	public void blockParkingOwner(String email) {
		searchField.sendKeys(email, Keys.ENTER);
		waitForElementInvisibility(loader);
		for(WebElement user: userEntry) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText();
			if(mail.equalsIgnoreCase(email)) {
				WebElement editBtn = user.findElement(By.cssSelector("td:last-child a:nth-child(2)"));
				Actions ac = new Actions(driver);
				ac.moveToElement(editBtn).doubleClick().perform();
				waitForElementVisibility(profileTitle);
				waitForElementVisibility(UserStatus);
				
				if(UserStatus.getText().equalsIgnoreCase("active")) {
					blockBtn.click();
					waitForElementVisibility(unblockBtn);
					waitForElementVisibility(SuccessToast);
					profileTitle.click();
					}
				break;
				}
			}	
	}
	
	
	public void unblockParkingOwner(String email) {
		searchField.sendKeys(email, Keys.ENTER);
		waitForElementInvisibility(loader);
		for(WebElement user: userEntry) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText();
			if(mail.equalsIgnoreCase(email)) {
				WebElement editBtn = user.findElement(By.cssSelector("td:last-child a:nth-child(2)"));
				Actions ac = new Actions(driver);
				ac.moveToElement(editBtn).doubleClick().perform();
				waitForElementVisibility(profileTitle);
				waitForElementVisibility(UserStatus);
				
				if(UserStatus.getText().equalsIgnoreCase("blocked")) {
					unblockBtn.click();
					waitForElementVisibility(blockBtn);
					waitForElementVisibility(SuccessToast);
					profileTitle.click();
					}
				break;
				}
			}	
	}
	
	
	
	public void DeleteParkingOwner(String email) {
		searchField.sendKeys(email, Keys.ENTER);
		waitForElementInvisibility(loader);
		for(WebElement user: userEntry) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText();
			if(mail.equalsIgnoreCase(email)) {
				user.findElement(By.cssSelector("td:last-child a:last-child")).click();
				waitForElementVisibility(confirmation);
				confrmYes.click();
				break;
			}
		
		}
	}
	
	

}
