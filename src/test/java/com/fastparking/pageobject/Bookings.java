package com.fastparking.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Bookings {
	WebDriver driver;
	int totalPages;
	int flag;
	
	
	//constructor
	public Bookings(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//locators
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> bookingsList;
	
	@FindBy(css="div[class*='pagination'] ul li[title='next page']")
	WebElement lastPagination;
	
	@FindBy(css="div[class*='pagination'] ul li")
	public List<WebElement> pagination;
	
	@FindBy(css=".status")
	WebElement loader;
	
	@FindBy(css=".css-1hwfws3")
	WebElement ownerSelect;
	
	@FindBy(css=".css-11unzgr div")
	List<WebElement> ownersList;
	
	@FindBy(css=".css-26l3qy-menu")
	WebElement ownersListBox;
	
	@FindBy(css="select[name='status']")
	WebElement statusDropdown;
	
	@FindBy(css="div[class='row'] p:first-child ")
	WebElement BookingDetailsBookingID;
	
	//methods
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public int TotalBookings() {
		int allBooking =bookingsList.size();
		totalPages = pagination.size()-1;
		for(int i=0; i<totalPages-1; i++) {
			lastPagination.click();
			waitForElementInvisibility(loader);
			allBooking = allBooking + bookingsList.size();
		}
		return allBooking;
	}
	
	
	public int ParticulerOwnerBooking(String ownerName, String status) throws InterruptedException {
		waitForElementInvisibility(loader);
		Actions ac = new Actions(driver);
		ac.moveToElement(ownerSelect).click().perform();
		waitForElementVisibility(ownersListBox);
		
		for(WebElement owner: ownersList) {
			waitForElementVisibility(owner);
			String value = owner.getText();
			if(value.equalsIgnoreCase(ownerName)) {
				owner.click();
				break;
			}
		}
		
		Select sc = new Select(statusDropdown);
		sc.selectByVisibleText(status);
		
		waitForElementInvisibility(loader);
		
		int bookingCount =bookingsList.size();
		totalPages = pagination.size()-1;
		
		for(int i=0; i<totalPages-1; i++) {
			lastPagination.click();
			waitForElementInvisibility(loader);
			bookingCount = bookingCount + bookingsList.size();
		}
		return bookingCount;
		
	}
	
	
	
	public String viewBookingDetails(String OrderID) {
		String bookingDetailID="";
		for(WebElement booking:bookingsList) {
			String ID = booking.findElement(By.cssSelector("td:first-child")).getText();
			if(ID.equals(OrderID)) {
				booking.findElement(By.cssSelector("td:last-child a")).click();
				flag=1;
				waitForElementInvisibility(loader);
				String[] bookDetailID = BookingDetailsBookingID.getText().split(":");
				String newID= bookDetailID[1].trim();
				bookingDetailID = newID;
				break;
			}
		}
		return bookingDetailID;
	}
	
	
	public String searchAndViewBookingDetails(String OrderID) {
		String bookingDetailID  = viewBookingDetails(OrderID);
		totalPages = pagination.size()-1;
		for(int i=0; i<totalPages-1; i++) {
			if(flag == 0) {
			lastPagination.click();
			//Thread.sleep(2000);
			waitForElementInvisibility(loader);
			}else {
				break;
			}
			bookingDetailID = viewBookingDetails(OrderID);
		}
		flag=0;	
		return bookingDetailID;
	}
	
	
	
	
	
	
	
	

}
