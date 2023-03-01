package rahulshettyacademy;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class Base2 
{
	public AndroidDriver driver;
	@BeforeClass
	public void launch()
	{
		DesiredCapabilities caps = new DesiredCapabilities();

		caps.setCapability("deviceName", "emulator-5554");

		//caps.setCapability("udid", "RZ8N605CWKV"); //Give Device ID 
		 
		caps.setCapability("platformName", "Android");

		//caps.setCapability("platformVersion", "13");

		caps.setCapability("appPackage", "com.androidsample.generalstore");

		caps.setCapability("appActivity", "com.androidsample.generalstore.SplashActivity");
		
		caps.setCapability("automationName", "UiAutomator2");

		caps.setCapability("noReset", "true");
		try {
			
	        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
	        } 
		catch (MalformedURLException e) {

	        System.out.println(e.getMessage());

	    }	
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
	
	public double changedatatype(String expect)
	{
		double d2=Double.parseDouble(expect);
		return d2;
	}
	
	@AfterClass
	public void teardown()
	{
		//close the application
		driver.quit();
				
	}
}
