package com.fastparking.pageobject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class PermitCards {

	WebDriver driver;
	
	//Constructor
	public PermitCards(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(xpath="//button[text()='Assign Permit Card']")
	WebElement assignPermitCardBtn;
	
	@FindBy(xpath="//h3[text()='Assign Permit Card']")
	WebElement assignPermitCardTtitle;
	
	@FindBy(css="div[role='status']")
	WebElement loader;
	
	@FindBy(xpath="(//div[@class=' css-1hwfws3'])[1]")
	WebElement userInput;
	
	@FindBy(css="div[class=' css-1n7v3ny-option']")
	List<WebElement> userEmailList;
	
	@FindBy(xpath="(//div[@class=' css-1hwfws3'])[2]")
	WebElement ownerInput;
	
	@FindBy(css="div[class=' css-1n7v3ny-option']")
	List<WebElement> ownerEmailList;
	
	@FindBy(xpath="(//div[@class=' css-1hwfws3'])[3]")
	WebElement vehicalInput;
	
	@FindBy(css="div[class=' css-1n7v3ny-option']")
	List<WebElement> vehicalList;
	
	@FindBy(css="input[name='cardNumber']")
	WebElement permitCardNumber;
	
	@FindBy(css="button[type='submit']")
	WebElement submit;
	
	@FindBy(css=".toast-message")
	public WebElement SuccessToast;
	
	@FindBy(css=".userlogout a")
	public WebElement logout;
	
	//methods
	
	public void waitForElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementInvisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void assignPermitCard(String userMail, String ownermail, String vehicalNumber, String cardNumber) throws InterruptedException {
		assignPermitCardBtn.click();
		waitForElementVisibility(assignPermitCardTtitle);
		Assert.assertTrue(assignPermitCardTtitle.isDisplayed());
		
		Actions ac  = new Actions(driver);
		
		Action src = ac.moveToElement(userInput).click()
					.sendKeys(userMail).build();
		
		src.perform();
		
		for(WebElement userEmail: userEmailList) {
			waitForElementVisibility(userEmail);
			String value = userEmail.getText();
			if(value.equalsIgnoreCase(userMail)) {
				userEmail.click();
				break;
			}
		}

		
		Action src1 = ac.moveToElement(ownerInput).click()
				.sendKeys(ownermail).build();
		
		src1.perform();
		
		for(WebElement ownerEmail: ownerEmailList) {
			waitForElementVisibility(ownerEmail);
			String value = ownerEmail.getText();
			System.out.println(value);
			if(value.equalsIgnoreCase(ownermail)) {
				ownerEmail.click();
				break;
			}
		}
		
		Action src2 = ac.moveToElement(vehicalInput).click()
				.sendKeys(vehicalNumber).build();
		
		src2.perform();
		

		for(WebElement vehical:vehicalList) {
			waitForElementVisibility(vehical);
			String value = vehical.getText();
			if(value.equalsIgnoreCase(vehicalNumber)) {
				vehical.click();
				break;
			}
		}
		
		permitCardNumber.sendKeys(cardNumber);
		
		
		submit.click();
		
		
}
	
}
