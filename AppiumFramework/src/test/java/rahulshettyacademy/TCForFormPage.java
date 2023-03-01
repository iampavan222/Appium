package rahulshettyacademy;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulshettyacademy.pages.android.FormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.opentelemetry.sdk.metrics.data.SumData;

public class TCForFormPage extends Base2 
{
	@Test
	public void cart() throws InterruptedException
	{
		FormPage page= new FormPage(driver);
		page.setName("Pavan Shetty");
		page.setGender("female");
		page.setCity("Argentina");
		page.submitForm();
		
	    driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	    driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
	    driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	    Thread.sleep(3000);
	    WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.attributeContains
	    		(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),
	    				"text", "Cart"));
	    List<WebElement> prodprice=driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	    int count= prodprice.size();
	    double sum=0;
	    for(int i=0;i<count;i++)
	    {
	    	String s1= prodprice.get(i).getText().substring(1);
	    	double d=Double.parseDouble(s1);
	    	sum=sum+d;
	    }
	    System.out.println("The total order sum is::"+sum);
	    String expect=driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().substring(1);
	    double d2=changedatatype(expect);
	    Assert.assertEquals(sum, d2);
	    if(sum==d2)
	    {
	    	System.out.println("test pass");
	    }
	    else
	    {
	    	System.out.println("test fails....");
	    }
	    Thread.sleep(5000);
	    
	    WebElement ele=driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
	    longpress(ele);
	    String alert=driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
	    if(alert.contains("Terms"))
	    {
	    	 driver.findElement(By.xpath("//android.widget.Button[@text='CLOSE']")).click();
	    }
	    else
	    {
	    	System.out.println("Leave it!!!");
	    }
	    
	    driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
	    driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
	    
	    Thread.sleep(5000);
	   
	    //Hybrid- native app
	    Set<String> contextnames=driver.getContextHandles();
	    for(String context:contextnames)
	    {
	    	System.out.println(context);
	    }
	   driver.context("WEBVIEW_com.androidsample.generalstore");
	   
	   driver.findElement(By.name("q")).sendKeys("Amazon");
	   Thread.sleep(2000);
	   driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	   Thread.sleep(2000);
	   driver.pressKey(new KeyEvent(AndroidKey.BACK));
	   
	   driver.context("NATIVE_APP");
	   
	    
	}

}
