package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctions.FunctionLibrary;
import config.Apputil;
import utilites.ExcelFileUtile;

public class DriverScript extends Apputil{
	String inputpath="./FileInput/LoginData_lyst3815.xlsx";
	String outputpath="./FileOutput/Datadrivenresults.xlsx";
	boolean res=false;
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void starttest() throws Throwable
	{
		//define path of html
		report= new ExtentReports("./Reports/login.html");
		//create object for Excel util class
		ExcelFileUtile xl=new ExcelFileUtile(inputpath);
		//count no of rows in login sheet
		int rc= xl.rowcount("Login");
		Reporter.log("no of rows are::"+rc,true);
		//iterate all rows in login sheet
		for (int i=1;i<=rc;i++)
		{
			//test case starts here
			logger=report.startTest("Validate login");
			//read username and password cells
			String username=xl.getcelldata("login",i,0);
			String password=xl.getcelldata("login",i,1);
			//call login method from function library class
			res=FunctionLibrary.verify_login(username, password);
			if(res)
			{
		//if res is true write as a login success into results cell
				xl.setcelldata("Login",i,2,"login success", outputpath);
				//write as pass into status cell
				xl.setcelldata("login",i,3,"pass",outputpath);
				logger.log(LogStatus.PASS,"valid username and password");
			}
			else
			{
				//take screenshot for fail steps
				File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./screenshot/iterations/"+i+"loginpage.png"));
				//capture error message
				String error_message=driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
				xl.setcelldata("login",i,2,error_message,outputpath);
				xl.setcelldata("login",i,3,"fail",outputpath);
				logger.log(LogStatus.FAIL, error_message);
			}
			report.endTest(logger);
			report.flush();
				
			}
		}
		
	}
	
	
	


