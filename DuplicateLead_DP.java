package LeafTapsTestCase_TESTNG;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DuplicateLead_DP extends BaseClass_LeafTaps {
	
	@Test(dataProvider="SendData")
	public void duplicateLead(String email) throws InterruptedException
	{
		
	
	driver.findElement(By.linkText("Find Leads")).click();
	driver.findElement(By.xpath("//span[text()='Email']")).click();
	driver.findElement(By.xpath("//input[@name='emailAddress']")).sendKeys(email);
	driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
	Thread.sleep(3000);
	String name = driver.findElement(By.xpath("//div[@class='x-grid3-hd-inner x-grid3-hd-firstName']/following::tr[1]/td[3]/div/a[1]")).getText();
	System.out.println("name: "+name);
	driver.findElement(By.xpath("//div[@class='x-grid3-hd-inner x-grid3-hd-firstName']/following::tr[1]/td[3]/div/a[1]")).click();
	driver.findElement(By.linkText("Duplicate Lead")).click();
	String titleLead=driver.findElementByXPath("//div[@id='sectionHeaderTitle_leads']").getText();
	if(titleLead.equals("Duplicate Lead"))
	{
		System.out.println("Duplicate Title Is Verified");
	}
	else
		System.out.println("Title Is Not Same");
	 driver.findElement(By.className("smallSubmit")).click();
	 String firstNameCheck = driver.findElement(By.xpath("//span[@id='viewLead_firstName_sp']")).getText();	
	 System.out.println("Duplicate name Created is : "+firstNameCheck);
	 if(name.equals(firstNameCheck))
	 {
		 System.out.println("The duplicated lead name is same As The Original name");
	 }
	 else
	 {
		 System.out.println("Lead names are different Check again");
	 }
	 driver.findElement(By.xpath("//a[text()='Duplicate Lead']")).click();
	 driver.findElement(By.xpath("//input[@value='Create Lead']")).click();
	 Thread.sleep(5000);
	
}

	@DataProvider
	public String[][] SendData() throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\DuplicateLead_Assignment.xlsx");
		XSSFSheet ws = wb.getSheet("Sheet1");
		//XSSFRow row = ws.getRow(0);
		int lastRowNum = ws.getLastRowNum();
		int lastCellNum = ws.getRow(0).getLastCellNum();
		String[][]  data=new String[lastRowNum][lastCellNum];
		for(int i=1;i<=lastRowNum;i++)
		{
			for(int j=0;j<lastCellNum;j++)
			{
				String text=ws.getRow(i).getCell(j).getStringCellValue();
				
				System.out.println(text);
				
				data[i-1][j]=ws.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		wb.close();
		return data;
	}
}
