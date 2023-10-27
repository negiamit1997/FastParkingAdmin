package com.fastparking.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import org.asynchttpclient.ws.WebSocketUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionUtil;

public class ManageUsers {
	WebDriver driver;
	
	
	//constructor
	public ManageUsers(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(css="table tbody tr:last-child td:last-child a:last-child")
	public WebElement tableLastrow;
	
	@FindBy(xpath="//table/tbody/tr")
	List<WebElement> userEntry;
	
	@FindBy(css="div[class*='pagination'] ul li[title='next page']")
	WebElement lastPagination;
	
	@FindBy(css="div[class*='pagination'] ul li")
	public List<WebElement> pagination;
	

	@FindBy(css=".infoimg")
	WebElement confirmation;
	
	@FindBy(xpath="//button[text()='Yes, delete it!']")
	WebElement confrmYes;
	
	@FindBy(css=".toast-message")
	public WebElement SuccessToast;
	
	
	@FindBy(linkText="Profile")
	WebElement profileTitle;

	@FindBy(xpath="//button[text()='Block']")
	WebElement blockBtn;
	
	@FindBy(xpath="//button[text()='Active']")
	WebElement unblockBtn;
	
	@FindBy(css=".status_active span")
	WebElement ativeUserStatus;
	
	@FindBy(xpath="//span[text()='blocked']")
	WebElement blockUserStatus;
	
	@FindBy(css=".status")
	WebElement loader;
	
	@FindBy(id="search-bar-0")
	WebElement searchBox;
	
	
	//methods
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	int totalPages;
	int flag;
	public int TotalUsers() throws InterruptedException {
		int count = userEntry.size();
		System.out.println(count);
		totalPages = pagination.size()-1;
		for(int i=0; i<totalPages-1; i++) {
			lastPagination.click();
			//Thread.sleep(2000);
			waitForElementInvisibility(loader);
			count = count + userEntry.size();
		}
		return count;
	}
	
	public void deleteUser(String email) throws InterruptedException {
		for(WebElement user:userEntry ) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
			if(mail.contains(email)) {
				user.findElement(By.cssSelector("td:last-child a:last-child")).click();
				flag = 1;
				waitForElementVisibility(confirmation);
				confrmYes.click();
				break;
			}
			
		}
	}
	
	public void searchAndDeleteUser(String email) throws InterruptedException {
		deleteUser(email);
	totalPages = pagination.size()-1;
	for(int i=0; i<totalPages-1; i++) {
		if(flag == 0) {
		lastPagination.click();
		//Thread.sleep(2000);
		waitForElementInvisibility(loader);
		}else {
			break;
		}
		deleteUser(email);
	}
	flag=0;		
	}
	
	public void blockUser(String email) throws InterruptedException {
		for(WebElement user:userEntry ) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
			if(mail.contains(email)) {
				WebElement viewBtn = user.findElement(By.cssSelector("td:last-child a:first-child"));
				Actions action = new Actions(driver);
				action.moveToElement(viewBtn).doubleClick().perform();
				flag = 1;
				waitForElementVisibility(profileTitle);
				waitForElementVisibility(ativeUserStatus);
				if(ativeUserStatus.getText().equalsIgnoreCase("active")) {
					blockBtn.click();
					waitForElementVisibility(unblockBtn);
					waitForElementVisibility(SuccessToast);
				}
				break;
			}
		}
	}
	
	public void SearchAndBlockUser(String email) throws InterruptedException {
		blockUser(email);
		totalPages = pagination.size()-1;
		for(int i=0; i<totalPages-1; i++) {
			if(flag == 0) {
			lastPagination.click();
			//Thread.sleep(2000);
			waitForElementInvisibility(loader);
			}else {
				break;
			}
			blockUser(email);
		}
	}
	
	
	public void unBlockUser(String email) throws InterruptedException {
		for(WebElement user:userEntry ) {
			String mail = user.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
			if(mail.contains(email)) {
				WebElement viewBtn = user.findElement(By.cssSelector("td:last-child a:first-child"));
				Actions action = new Actions(driver);
				action.moveToElement(viewBtn).doubleClick().perform();
				flag = 1;
				waitForElementVisibility(profileTitle);
				//Thread.sleep(3000);
				waitForElementInvisibility(loader);
				waitForElementVisibility(blockUserStatus);
				System.out.println("............................");
				System.out.println(blockUserStatus.getText());
				if(blockUserStatus.getText().equalsIgnoreCase("blocked")) {
					System.out.println("/////////////////////////////");
					unblockBtn.click();
					waitForElementVisibility(blockBtn);
					waitForElementVisibility(SuccessToast);
				}
				break;
			}
		}
	}
	
	
	
	public void SearchAndunBlockUser(String email) throws InterruptedException {
		unBlockUser(email);
		totalPages = pagination.size()-1;
		for(int i=0; i<totalPages-1; i++) {
			if(flag == 0) {
			lastPagination.click();
			//Thread.sleep(2000);
			waitForElementInvisibility(loader);
			}else {
				break;
			}
			unBlockUser(email);
		}
	}
	
	
	
	
   public String searchUser(String mail) throws AWTException {
	   searchBox.sendKeys(mail);
	   Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		waitForElementInvisibility(loader);
		String email = driver.findElement(By.cssSelector("td:nth-child(3)")).getText();
		return email;
	   
	   
   }
	
	
}

