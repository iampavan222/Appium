package rahulshettyacademy;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base 
{
	public AndroidDriver driver ;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void configappium() throws MalformedURLException
	{
		//set the server 
		service = new AppiumServiceBuilder()
		 .withAppiumJS(new File("C:\\Users\\PAVAN\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
		 .withIPAddress("127.0.0.1").usingPort(4723).build();
		
		//start server
		service.start();
		
		//set the emulator
		UiAutomator2Options opt = new UiAutomator2Options();
		opt.setDeviceName("emulator-5554");
		/* hiding the real device
		opt.setDeviceName("8e1006f");
		  */
		 
		opt.setChromedriverExecutable("D:\\Java-Selenium\\Appium\\src\\test\\java\\ChromeDriver\\chromedriver.exe");
		opt.setApp(System.getProperty("user.dir")+ "\\src\\test\\java\\resources\\General-Store.apk");
		
		
		//init and create an obj through drivers like andriod ,io
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), opt);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
				
		//close the server
		service.stop();
	}

}
