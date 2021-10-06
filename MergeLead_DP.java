package LeafTapsTestCase_TESTNG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MergeLead_DP extends BaseClass_LeafTaps{
	@Test
	public void MergeLead() throws InterruptedException
	{
		WebElement contacts=driver.findElement(By.linkText("Contacts"));
		contacts.click();
		WebElement mergeContact=driver.findElement(By.linkText("Merge Contacts"));
		mergeContact.click();
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following-sibling::a")).click();
		Set<String> windowHandlesOfMerge = driver.getWindowHandles();
		List<String> listWinHandle=new ArrayList<String>(windowHandlesOfMerge);
		driver.switchTo().window(listWinHandle.get(1));
		System.out.println("Control Transferred to new window");
		String title2 = driver.getTitle();
		System.out.println(title2);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(listWinHandle.get(0));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']//following-sibling::a")).click();
		
		Set<String> windowHandlesSecond = driver.getWindowHandles();
		List<String> listWinHandle2=new ArrayList<String>(windowHandlesSecond);
		driver.switchTo().window(listWinHandle2.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(listWinHandle.get(0));
		
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		
		Alert alert=driver.switchTo().alert();
		alert.accept();
		
		String title3 = driver.getTitle();
		System.out.println("Title Of The Page after merging the contacts      "+title3);
		if(title3.equalsIgnoreCase("View Contact | opentaps CRM"))
		{
			System.out.println("Successfully Merged The contact");
		}
		else
			System.out.println("Merge Unsuccessful");
	}
	

}
