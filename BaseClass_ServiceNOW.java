package ServiceNow_TESTNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass_ServiceNOW {
	
	public ChromeDriver driver;
	@BeforeMethod
	public void login() throws InterruptedException
	{
		
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://dev112523.service-now.com");
	String title=driver.getTitle();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	System.out.println(title);
	Thread.sleep(1000);
	
	
	
	WebElement Frame1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
	driver.switchTo().frame(Frame1);
	
	driver.findElement(By.id("user_name")).sendKeys("admin");
	driver.findElement(By.id("user_password")).sendKeys("bfpfFW5RxP0W");
	driver.findElement(By.xpath("//button[text()='Log in']")).click();
	

}

}
