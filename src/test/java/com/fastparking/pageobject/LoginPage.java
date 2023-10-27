package com.fastparking.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class LoginPage {

	WebDriver driver;
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//locators
	@FindBy(css="input[type='email']")
	WebElement emailLocator;
	
	@FindBy(css="input[type='password']")
	public WebElement passwordLocator;
	
	@FindBy(id="nextBtn")
	WebElement LoginBtnLocator;
	
	@FindBy(linkText="Forgot Password?")
	WebElement ForgotPasswordLocator;
	
	@FindBy(css=".login_heading h3")
	WebElement FormTitleLocator;
	
	@FindBy(css=".user_content h4")
	WebElement AdminTextLocator;
	
	@FindBy(css="div[class*='alert']")
	public WebElement invalidCredentialsValidationLocator;
	
	@FindBy(css=".sign-in-top img")
	WebElement logoLocator;
	
	
	//methods
	public void doLogin(String email, String Password) {
		emailLocator.sendKeys(email);
		passwordLocator.sendKeys(Password);
		LoginBtnLocator.click();
	}
	
	public String getDashboardAdminText() {
		String value = AdminTextLocator.getText();
		return value;
	}
	
	public String getformTitle() {
		String text = FormTitleLocator.getText();
		return text;
	}
	
	public void goToForgotPassword() {
		ForgotPasswordLocator.click();
	}
	
	public String getInvalidCredentialsValidationText() {
		String value = invalidCredentialsValidationLocator.getText();
		return value;
	}
	
	
	
	public void clearInputFieldData(){
		emailLocator.clear();
		passwordLocator.clear();
	}
	
	
	public Screenshot getLogo() {
		Screenshot image = new AShot().takeScreenshot(driver,logoLocator);
		return image;
	}
	
	
}
