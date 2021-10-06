package LeafTapsTestCase_TESTNG;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateLead_DP extends BaseClass_LeafTaps{
	

@Test(dataProvider="SendData")
	public  void RunCreateLead(String cname,String fname,String lname,String flocal,String local,String bday,String Salu,String Dept,String arev,String emp,
			String sic,String csym,String desc,String imp,String areacode,
			String phoext,String mailid,String phoneask,String pnumber,String purl,String genadd,
			String add2,String add3,String city,String pcode,String pcodeexn,String pcodeexten,String statepro) {
		
		driver.findElement(By.linkText("Create Lead")).click();
		
		
		WebElement companyName=driver.findElement(By.id("createLeadForm_companyName"));
		companyName.sendKeys(cname);
		WebElement firstName=driver.findElement(By.id("createLeadForm_firstName"));
		firstName.sendKeys(fname);
		WebElement lastName=driver.findElement(By.id("createLeadForm_lastName"));
		lastName.sendKeys(lname);
		WebElement firstLocal=driver.findElement(By.id("createLeadForm_firstNameLocal"));
		firstLocal.sendKeys(flocal);
		WebElement lastLocal=driver.findElement(By.id("createLeadForm_lastNameLocal"));
		lastLocal.sendKeys(local);
		WebElement birthday=driver.findElement(By.id("createLeadForm_birthDate"));
		birthday.sendKeys(bday);
		WebElement salutation=driver.findElement(By.id("createLeadForm_personalTitle"));
		salutation.sendKeys(Salu);
		WebElement department=driver.findElement(By.id("createLeadForm_departmentName"));
		department.sendKeys(Dept);
		WebElement annualRevenue=driver.findElement(By.id("createLeadForm_annualRevenue"));
		annualRevenue.sendKeys(arev);
		WebElement noOfEmp=driver.findElement(By.id("createLeadForm_numberEmployees"));
		noOfEmp.sendKeys(emp);
		driver.findElement(By.id("createLeadForm_sicCode")).sendKeys(sic);
		driver.findElement(By.id("createLeadForm_tickerSymbol")).sendKeys(csym);
		driver.findElement(By.id("createLeadForm_description")).sendKeys(desc);
		driver.findElement(By.id("createLeadForm_importantNote")).sendKeys(imp);
		WebElement countryCode=driver.findElement(By.id("createLeadForm_primaryPhoneCountryCode"));
		countryCode.clear();
		countryCode.sendKeys("4");
		driver.findElement(By.id("createLeadForm_primaryPhoneAreaCode")).sendKeys(areacode);
		driver.findElement(By.id("createLeadForm_primaryPhoneExtension")).sendKeys(phoext);
		driver.findElement(By.id("createLeadForm_primaryEmail")).sendKeys(mailid);
		driver.findElement(By.id("createLeadForm_primaryPhoneAskForName")).sendKeys(phoneask);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(pnumber);
		
		driver.findElement(By.id("createLeadForm_primaryWebUrl")).sendKeys(purl);
		
		driver.findElement(By.id("createLeadForm_generalToName")).sendKeys(genadd);
		driver.findElement(By.id("createLeadForm_generalAttnName")).sendKeys(add2);
		
		driver.findElement(By.id("createLeadForm_generalAddress1")).sendKeys(add2);
		driver.findElement(By.id("createLeadForm_generalAddress2")).sendKeys(add3);
		driver.findElement(By.id("createLeadForm_generalCity")).sendKeys(city);
		driver.findElement(By.id("createLeadForm_generalPostalCode")).sendKeys(pcode);
		driver.findElement(By.id("createLeadForm_generalPostalCodeExt")).sendKeys(pcodeexn);
		driver.findElement(By.id("createLeadForm_generalPostalCodeExt")).sendKeys(pcodeexten);
		driver.findElement(By.id("createLeadForm_generalStateProvinceGeoId")).sendKeys(statepro);
		driver.findElement(By.className("smallSubmit")).click();
}
@DataProvider
public String[][] SendData() throws IOException
{
	XSSFWorkbook wb=new XSSFWorkbook("C:\\Users\\Vinoth\\OneDrive\\Documents\\Janu\\Workspace\\MavenProject\\Data\\CreateLead_Assignment.xlsx");
	XSSFSheet ws = wb.getSheet("CreateLead");
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
	System.out.println("Storing in array");
	for(int i=0;i<lastRowNum;i++)
	{
		for(int j=0;j<lastCellNum;j++)
		{
			System.out.print(data[i][j]);
		}
		System.out.println();
	}
	return data;
	
}
}