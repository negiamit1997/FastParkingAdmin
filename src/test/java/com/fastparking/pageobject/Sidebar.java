package com.fastparking.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Sidebar {
	
	WebDriver driver;
	
	public Sidebar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="#side-menu li a")
	List<WebElement> sidebarList;
	
	public void getTab(String tabName) {
		for(WebElement item:sidebarList) {
			String name = item.getText();
			if(name.equalsIgnoreCase(tabName)) {
				item.click();
				break;
			}
		}
	}
	
	
}
