package ServiceNow_TESTNG;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateInstance_DP extends BaseClass_ServiceNOW  {
	@Test(dataProvider="SendData")	
	public  void CreateInstance(String sys) throws InterruptedException, IOException {
		
		
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incidents");
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		
		WebElement Frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(Frame2);
		
		driver.findElement(By.xpath("//b[text()='All']")).click();
		driver.findElement(By.xpath("//button[@class='selected_action action_context btn btn-primary']")).click();
		WebElement instanceNum = driver.findElement(By.id("incident.number"));
		String Nume=instanceNum.getAttribute("value");
		System.out.println("The Instance Created Is :  "+Nume);
		
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']/span")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> WindHand=new ArrayList<String>(windowHandles);
		driver.switchTo().window(WindHand.get(1));
		String title2 = driver.getTitle();
		System.out.println(title2);
		
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys("System Adminstrator",Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='glide_ref_item_link']")).click();
		
		
		driver.switchTo().window(WindHand.get(0));
		String title3 = driver.getTitle();
		System.out.println(title3);
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame3);
		
		
		
		//driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys("System Administrator");
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys(sys);
		driver.findElement(By.xpath("//button[@class='form_action_button  action_context btn btn-default'][1]")).click();
		//driver.findElement(By.xpath("//input[@name='5c1ae6b21bb630102e66a8e5604bcbfa_text']")).sendKeys(Nume);
		driver.findElement(By.xpath("//div[@class='input-group']/input")).sendKeys(Nume+Keys.ENTER);
		

}
	@DataProvider
	public String[][] SendData() throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\ServiceNow_CreateInstance.xlsx");
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