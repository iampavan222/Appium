package org.rahulshettyacademy.pages.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.rahulshetty.AppiumActions.ActionsGestures;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends ActionsGestures
{
	//create the local variable
	AndroidDriver driver;
	
	//associated the driver to this java class
	public FormPage(AndroidDriver driver)
	{
		//call the parent constructor
		super(driver);
		/*
		//i.e all the prop will send to driver to dummy driver
		this.driver=driver;
		//initilise the elements in pagefactory class and associated to the driver
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		*/
		
		//i.e all the prop will send to driver to dummy driver
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
		//Generic.wait(10);
	}
	
	
	//first find the locator
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement namefield;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement maleopt;
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement femaleopt;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement dropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement LetsShop;
	
	public void setName(String name) 
	{
		namefield.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender)
	{
		if(gender.contains("Female"))
			femaleopt.click();
		else
			maleopt.click();
	}

	public void setCity(String City)
	{
		dropdown.click();
		scrolluptoText(City);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+City+"']")).click();
	}
	
	public void submitForm()
	{
		LetsShop.click();
	}
	

}
