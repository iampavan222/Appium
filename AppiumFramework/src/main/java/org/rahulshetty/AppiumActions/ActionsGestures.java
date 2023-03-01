package org.rahulshetty.AppiumActions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ActionsGestures 
{
	AndroidDriver driver;
	
	public ActionsGestures(AppiumDriver driver)
	{
		this.driver=(AndroidDriver) driver;
	}
	public void longpress(WebElement ele)
	{
		 ((JavascriptExecutor)driver).executeScript("mobile:longClickGesture",
		    		ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
		    				"duration",2000));
	}
	
	public void scrollaction()
	{
		boolean canScrollMore;
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			));
		}
		while(canScrollMore);
	}
	
	public void scrolluptoText(String CountryName)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+CountryName+"\"));"));
	}
	
	public void swipe(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement)ele).getId(),
			    "direction", direction,
			    "percent", 0.75
			));	
	}
	public void drageanddrop(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", 824,
			    "endY", 712
			));
	}

}
