package com.fastparking.pageobject;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Parkings {

	WebDriver driver;
	
	//constructor
	public Parkings(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath="//button[text()='Add Parking']")
	WebElement addParkingBtn;
	
	@FindBy(css="div[role='status']")
	WebElement loader;
	
	@FindBy(css=".dropzone ")
	WebElement parkingImg;
	
	@FindBy(css=".parking-add-image-container img")
	WebElement imageDisplayed;
	
	@FindBy(css="input[name='building_name']")
	WebElement parkingName;
	
	@FindBy(css=".css-1wy0on6")
	WebElement parkingShortnameDropdown;
	
	@FindBy(css=".css-yt9ioa-option")
	List<WebElement> parkingShortName;
	
	@FindBy(css="input[name='parkingNumber']")
	WebElement parkingNumber;
	
	@FindBy(css="select[name='zoneId']")
	WebElement zone;
	
	@FindBy(xpath="(//div[@class='react-time-picker__inputGroup']/input[2])[1]")
	WebElement openingHrs;
	
	@FindBy(xpath="(//div[@class='react-time-picker__inputGroup']/input[2])[2]")
	WebElement closingHrs;
	
	@FindBy(xpath="(//div[@class='react-time-picker__inputGroup']/input[3])[1]")
	WebElement openingMins;
	
	@FindBy(xpath="(//div[@class='react-time-picker__inputGroup']/input[3])[2]")
	WebElement closingMins;
	
	@FindBy(xpath="(//div[@class=' css-1hwfws3'])[2]")
	WebElement ownerDropdown;
	
	@FindBy(css=".css-yt9ioa-option")
	List<WebElement> owners;
	
	@FindBy(xpath="(//div[@class=' css-1hwfws3'])[3]")
	WebElement location;
	
	@FindBy(css=".css-yt9ioa-option")
	List<WebElement> locationList;
	
	@FindBy(css="select[name='type']")
	WebElement type;
	
	@FindBy(css="input[name='floors']")
	WebElement floors;
	
	@FindBy(css="input[name='parkingSlots']")
	List<WebElement> parkingSlots;
	
	@FindBy(css="button[type='submit']")
	WebElement submit;
	
	@FindBy(css=".toast-message")
	public WebElement SuccessToast;
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> parkingEntry;
	
	@FindBy(id="search-bar-0")
	WebElement searchField;
	
	@FindBy(css="div[class*='pagination'] ul li")
	public List<WebElement> pagination;
	
	@FindBy(css="div[class*='pagination'] ul li[title='next page']")
	WebElement lastPagination;
	
	@FindBy(css=".infoimg")
	WebElement confirmation;
	
	@FindBy(xpath="//button[text()='Yes, delete it!']")
	WebElement confrmYes;
	
	@FindBy(css="div[class*='image-delete-icon'] svg")
	WebElement deleteParkingImage;
	
	@FindBy(xpath="//div[text()='Download']/preceding-sibling::*")
	WebElement qrCode;
	
	int totalPages;
	int flag;
	//methods
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void AddParking(String picName,String nameParking, String parkShortName, String parkNumber, String zoneName, String openHrs, String openMins, String closeHrs, String closeMins, String nameOwner, String address, String parkingType, String buildingFloor, String parkingSlot ) throws IOException, InterruptedException {
		addParkingBtn.click();
		waitForElementInvisibility(loader);
		parkingImg.click();
		Runtime.getRuntime().exec("F:\\automationtesting\\work\\FastParkingAdmin\\autoIt\\FileUpload.exe"+" "+picName);
		
		//waitForElementInvisibility(loader);
		waitForElementVisibility(imageDisplayed);
		parkingName.sendKeys(nameParking);
		parkingShortnameDropdown.click();
		for(WebElement parkName:parkingShortName) {
			String value = parkName.getText();
			if(value.equalsIgnoreCase(parkShortName)) {
				parkName.click();
				break;
			}
		}
		parkingNumber.sendKeys(parkNumber);
		Select sc = new Select(zone);
		sc.selectByVisibleText(zoneName);
		openingHrs.sendKeys(openHrs);
		openingMins.sendKeys(openMins);
		closingHrs.sendKeys(closeHrs);
		closingMins.sendKeys(closeMins);
		ownerDropdown.click();
		for(WebElement owner: owners) {
			String value = owner.getText();
			if(value.equalsIgnoreCase(nameOwner)) {
				owner.click();
				break;
			}
		}
		Actions ac = new Actions(driver);
		Action src = ac.moveToElement(location).click()
				.sendKeys(address).build();
		src.perform();
		
		for(WebElement loc: locationList) {
			String value = loc.getText().toLowerCase();
			if(value.contains(address)) {
				loc.click();
				break;
			}
		}
		Select sc1 = new Select(type);
		sc1.selectByVisibleText(parkingType);
		
		if(parkingType.equalsIgnoreCase("Building")) {
			floors.sendKeys(buildingFloor);
			
			for(WebElement slot: parkingSlots) {
				waitForElementVisibility(slot);
				slot.sendKeys(parkingSlot);
			}
		}else if(parkingType.equalsIgnoreCase("Street")) {
			for(WebElement slot: parkingSlots) {
				waitForElementVisibility(slot);
				slot.clear();
				slot.sendKeys(parkingSlot);
			}
		}
		//Thread.sleep(3000);
		submit.click();
		
	}
	
	
	public void editparking(String editPark, String picName,String nameParking, String parkShortName, String parkNumber, String zoneName, String openHrs, String openMins, String closeHrs, String closeMins, String nameOwner, String address, String parkingType, String buildingFloor, String parkingSlot) throws IOException {
		for(WebElement user:parkingEntry ) {
			String mail = user.findElement(By.cssSelector("td:first-child")).getText().trim();
			if(mail.contains(editPark)) {
				user.findElement(By.cssSelector("td:last-child a:first-child")).click();
				flag = 1;
				break;
			}
		}
		waitForElementInvisibility(loader);
		System.out.println("/////////////////////////////////////////////////////////");
		System.out.println(parkingName.getAttribute("value"));
		Assert.assertEquals(editPark, parkingName.getAttribute("value"));
		Assert.assertTrue(qrCode.isDisplayed());
		deleteParkingImage.click();
		parkingImg.click();
		Runtime.getRuntime().exec("F:\\automationtesting\\work\\FastParkingAdmin\\autoIt\\FileUpload.exe"+" "+picName);
		
		waitForElementVisibility(imageDisplayed);
		parkingName.clear();
		parkingName.sendKeys(nameParking);
		parkingShortnameDropdown.click();
		for(WebElement parkName:parkingShortName) {
			String value = parkName.getText();
			if(value.equalsIgnoreCase(parkShortName)) {
				parkName.click();
				break;
			}
		}
		
		parkingNumber.clear();
		parkingNumber.sendKeys(parkNumber);
		Select sc = new Select(zone);
		sc.selectByVisibleText(zoneName);
		
		openingHrs.clear();
		openingHrs.sendKeys(openHrs);
		openingMins.clear();
		openingMins.sendKeys(openMins);
		closingHrs.clear();
		closingHrs.sendKeys(closeHrs);
		closingMins.clear();
		closingMins.sendKeys(closeMins);
		ownerDropdown.click();
		for(WebElement owner: owners) {
			String value = owner.getText();
			if(value.equalsIgnoreCase(nameOwner)) {
				owner.click();
				break;
			}
		}
		
		Actions ac = new Actions(driver);
		Action src = ac.moveToElement(location).click()
				.sendKeys(address).build();
		src.perform();
		
		for(WebElement loc: locationList) {
			String value = loc.getText().toLowerCase();
			if(value.contains(address)) {
				loc.click();
				break;
			}
		}
		
		Select sc1 = new Select(type);
		sc1.selectByVisibleText(parkingType);
		
		if(parkingType.equalsIgnoreCase("Building")) {
			floors.clear();
			floors.sendKeys(buildingFloor);
			
			for(WebElement slot: parkingSlots) {
				waitForElementVisibility(slot);
				slot.clear();
				slot.sendKeys(parkingSlot);
			}
		}else if(parkingType.equalsIgnoreCase("Street")) {
			for(WebElement slot: parkingSlots) {
				waitForElementVisibility(slot);
				slot.clear();
				slot.sendKeys(parkingSlot);
			}
		}
		//Thread.sleep(3000);
		submit.click();
		
	}
	
	
	
	public String searchParking(String parkingName) {
		searchField.sendKeys(parkingName, Keys.ENTER);
		waitForElementInvisibility(loader);
		String name = "";
		for(WebElement user: parkingEntry) {
			name = user.findElement(By.cssSelector("td:first-child")).getText();
		}
		return name;
	}
	
	public String getParking(String parkingName) {
		String name = "";
		for(WebElement user: parkingEntry) {
			String value = user.findElement(By.cssSelector("td:first-child")).getText();
			if(value.equalsIgnoreCase(parkingName)) {
				name = value;
				flag=1;
				break;
			}
		}
		return name;
	}
	
	public String FindParking(String parkingName) throws InterruptedException {
		String parkName="";
		parkName = getParking(parkingName);
	totalPages = pagination.size()-1;
	for(int i=0; i<totalPages-1; i++) {
		if(flag == 0) {
		lastPagination.click();
		//Thread.sleep(2000);
		waitForElementInvisibility(loader);
		}else {
			break;
		}
		parkName = getParking(parkingName);
	}
	flag=0;	
	return parkName;
	}
	
	
	public void deleteparking(String name) {
		for(WebElement user:parkingEntry ) {
			String mail = user.findElement(By.cssSelector("td:first-child")).getText().trim();
			if(mail.contains(name)) {
				user.findElement(By.cssSelector("td:last-child a:last-child")).click();
				flag = 1;
				waitForElementVisibility(confirmation);
				confrmYes.click();
				break;
			}
			
		}
	}
	
	public void searchAndDeleteParking(String name) throws InterruptedException {
		deleteparking(name);
	totalPages = pagination.size()-1;
	for(int i=0; i<totalPages-1; i++) {
		if(flag == 0) {
		lastPagination.click();
		//Thread.sleep(2000);
		waitForElementInvisibility(loader);
		}else {
			break;
		}
		deleteparking(name);
	}
	flag=0;		
	}
	
	
}
