package LeafTapsTestCase_TESTNG;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DeleteLead_DP extends BaseClass_LeafTaps{
	
	@Test(dataProvider="sendData")
	public void deleteLead(String pno) throws InterruptedException
	{
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.linkText("Phone")).click();
		driver.findElement(By.name("phoneCountryCode")).clear();
		driver.findElement(By.name("phoneNumber")).sendKeys(pno);
		//driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		 Thread.sleep(2000);
		 String idLead = driver.findElement(By.xpath("//td[1]/div[@class='x-grid3-hd-inner x-grid3-hd-partyId']/following::tr[1]/td[1]/div/a[1]")).getText(); 
		 System.out.println(idLead);
		 driver.findElement(By.xpath("//td[1]/div[@class='x-grid3-hd-inner x-grid3-hd-partyId']/following::tr[1]/td[1]/div/a[1]")).click();
		 driver.findElement(By.linkText("Delete")).click();
		 driver.findElement(By.linkText("Find Leads")).click();
		 driver.findElement(By.name("id")).sendKeys(idLead);
		 driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		 WebElement result = driver.findElement(By.xpath("//div[contains(text(),'No records')]"));
		 boolean result1=result.isDisplayed();
		 if(result1==true)
		 {
			 System.out.println("No Leads are found in the given id");
		 }
		 else
		 {
			 System.out.println("Lead Id is found and it is not deleted");
		 }
		 Thread.sleep(2000);
		driver.close();

	}
	@DataProvider
	public String[][] sendData() throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\DeleteLead_Assignment.xlsx");
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