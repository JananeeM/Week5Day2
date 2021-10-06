package ServiceNow_TESTNG;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UpdateInstance_DP extends BaseClass_ServiceNOW{

	@Test(dataProvider="sendData")
	public void updateInstance(String number)
	{
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incidents");
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		WebElement Frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(Frame2);
		//WebElement Frame3 =driver.findElement(By.xpath("//iframe[@id='templateIframe']"));
		//driver.switchTo().frame(Frame3);
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(number,Keys.ENTER);
		driver.findElement(By.xpath("//td[@class='vt'][1]")).click();
		WebElement Urgency = driver.findElement(By.xpath("//select[@name='incident.urgency']"));
		Select drop=new Select(Urgency);
		drop.selectByIndex(0);
		
		WebElement State = driver.findElement(By.xpath("//select[@name='incident.state']"));
		Select drop1=new Select(State);
		drop1.selectByIndex(1);
		
		
		driver.findElement(By.xpath("//textarea[@id='activity-stream-work_notes-textarea']")).sendKeys("Important");
		driver.findElement(By.xpath("//button[@class='form_action_button  action_context btn btn-default'][1]")).click();
		
		
	}
	
	@DataProvider
	public String[][] sendData() throws IOException
	{
		XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\ServiceNow_AssignInstance.xlsx");
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
