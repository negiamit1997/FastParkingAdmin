package com.fastparking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadConfig {

	Properties prop;
	
	String path = System.getProperty("user.dir") + "\\Configuration\\config.properties";
	
	//constructor
	public ReadConfig() {
		prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);
			
		} catch (Exception e) {
			System.out.println(e);
		}
			
	}
	
	
	public String getBaseURL() {
		String value = prop.getProperty("baseURL");
		if(value!=null) {
			return value;
		}else {
			throw new RuntimeException("Base url value is not specified in config file");
		}
	}
	
	
	public String getBrowser() {
		String value = prop.getProperty("browser");
		
		if(value!=null) {
			return value;
		}else {
			throw new RuntimeException("Browser value is not specified in config file");
		}		
	}
	
	
	public String getEmailAddress() {
		String value = prop.getProperty("email");
		return value;
	}
	
	public String getPassword() {
		String value = prop.getProperty("password");
		return value;
	}
	
	public String getLoginPageValidation_IC() {
		String value = prop.getProperty("LoginPageInvalidCredentialsValidation");
		return value;
	}
	
	public String getDeleteUser() {
		String value = prop.getProperty("DeleteUser");
		return value;
		
	}
	
	public String getDataUpdateSuccessMessage() {
		String value = prop.getProperty("DataUpdateSuccessMessage");
		return value;
	}
	
	
	public String getBlockUser() {
		String value = prop.getProperty("BlockUser");
		return value;
		
	}
	
	
	public String getUnblockUser() {
		String value = prop.getProperty("unBlockUser");
		return value;
		
	}
	
	public String getSearchUser() {
		String value = prop.getProperty("SearchUser");
		return value;
	}
	
	public String getOwnerAddMsz() {
		String value = prop.getProperty("OwnerAddedMsz");
		return value;
	}
	
	
}
