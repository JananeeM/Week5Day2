package LeafTapsTestCase_TESTNG;

import java.io.IOException;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditLead_DP extends BaseClass_LeafTaps{
	@Test(dataProvider="sendData")
	public void EditLead(String name,String company) throws InterruptedException
	{
		driver.findElement(By.linkText("Find Leads")).click();
		
		driver.findElement(By.xpath("(//input[@name='firstName'])[3]")).sendKeys(name);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(5000);
		String textName = driver.findElement(By.xpath("//div[@class='x-grid3-hd-inner x-grid3-hd-firstName']/following::tr[1]/td[3]/div/a[1]")).getText();
		System.out.println("name: "+textName);
		driver.findElement(By.xpath("//div[@class='x-grid3-hd-inner x-grid3-hd-firstName']/following::tr[1]/td[3]/div/a[1]")).click();
		String viewTitle = driver.findElement(By.xpath("//div[text()='View Lead']")).getText();
		if(viewTitle.equals("View Lead"))
		 			 System.out.println("view Lead is verified");		 
		 else		 
			 System.out.println("Title not verified");
		driver.findElement(By.linkText("Edit")).click();
		driver.findElement(By.id("updateLeadForm_companyName")).clear();
		driver.findElement(By.id("updateLeadForm_companyName")).sendKeys(company);
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		String nameC = driver.findElement(By.xpath("//span[@id='viewLead_companyName_sp']")).getText();	
		 System.out.println("newcompanyName is : "+nameC);
		 
		 if(nameC.contains("New Company name"))
		 {
			 System.out.println("Company name is updated successfully");
		 }
		 else
		 {
			 System.out.println("Company name is not updated");
		 }
		 Thread.sleep(5000);
		 
	}
	@DataProvider
	public String[][] sendData() throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\EditLead_Assignment.xlsx");
		XSSFSheet ws = wb.getSheet("Sheet1");
		//String text=
		int lastRowNum=ws.getLastRowNum();
		int lastCellNum=ws.getRow(0).getLastCellNum();
		String[][] data=new String[lastRowNum][lastCellNum];
		for(int i=1;i<=lastRowNum;i++)
		{
			for(int j=0;j<lastCellNum;j++)
			{
				data[i-1][j]=ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(data[i-1][j]);
			}
		}
		wb.close();
		return data;
		
	}

}
