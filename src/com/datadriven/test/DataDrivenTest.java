package com.datadriven.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestUtil.TestUtil;

public class DataDrivenTest {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe");
		driver= new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		Object data[][]=TestUtil.getTestData("logindata");
		return data;
		
	}
		
	@Test(dataProvider="getTestData")
	public void OrangeHrmLoginTest(String UserName, String Password) {
		
		driver.findElement(By.xpath("//input[@name='txtUsername']")).clear();
		driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys(UserName);
		
		driver.findElement(By.xpath("//input[@name='txtPassword']")).clear();
		driver.findElement(By.xpath("//input[@name='txtPassword']")).sendKeys(Password);
		
		driver.findElement(By.xpath("//input[@name='Submit']")).click();
	}
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
