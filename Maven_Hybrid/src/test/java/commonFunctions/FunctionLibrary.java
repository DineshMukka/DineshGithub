package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import config.Apputil;

public class FunctionLibrary extends Apputil {
	/*projectName: PrimusBank
	 * Module Name: Admin Module
	 * TesterName: Dinesh
	 * Creation Date:25/09/2023
	 */
	//method for login
	public static boolean adminLogin(String user,String pass)
	{
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected="adminflow";
		String actual=driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Sucess::"+expected+"  "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Failed::"+expected+"  "+actual,true);
			return false;
		}
	}

	//method for click branches button
	public static void ClickBranches()
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjBranches"))).click();
	}
	//method for branch creation
	public static boolean branchCreation(String Branchname,String Address1,String Address2,String Address3,
			String Area,String zipcode,String country,String State,String city) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjNewBranch"))).click();
		driver.findElement(By.xpath(conpro.getProperty("ObjBranchname"))).sendKeys(Branchname);	
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress1"))).sendKeys(Address1);		
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress2"))).sendKeys(Address2);		
		driver.findElement(By.xpath(conpro.getProperty("ObjAddress3"))).sendKeys(Address3);
		driver.findElement(By.xpath(conpro.getProperty("ObjArea"))).sendKeys(Area);		
		driver.findElement(By.xpath(conpro.getProperty("ObjZipcode"))).sendKeys(zipcode);	
		new Select(driver.findElement(By.xpath(conpro.getProperty("Objcountry")))).selectByVisibleText(country);
		new Select(driver.findElement(By.xpath(conpro.getProperty("Objstate")))).selectByVisibleText(State);
		new Select(driver.findElement(By.xpath(conpro.getProperty("Objcity")))).selectByVisibleText(city);
		driver.findElement(By.xpath(conpro.getProperty("ObjSubmit"))).click();
		//capture alert message
		String expected_alert =driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String actual_alert="New Branch with id";
		if(expected_alert.toLowerCase().contains(actual_alert.toLowerCase()))
		{
			Reporter.log(expected_alert,true);
			return true;
		}
		else
		{
			Reporter.log("New branch creation fail",true);
			return false;
		}	
	}
	//method for branch updation
	public static boolean branchUpdation(String BranchName,String Address1,String Area, String zipcode) throws Throwable
	{
		driver.findElement(By.xpath(conpro.getProperty("ObjEdit"))).click();
		WebElement element1 = driver.findElement(By.xpath(conpro.getProperty("ObjBranch")));
		element1.clear();
		element1.sendKeys(BranchName);
		WebElement element2 = driver.findElement(By.xpath(conpro.getProperty("ObjAddress")));
		element2.clear();
		element2.sendKeys(BranchName);
		WebElement element3 = driver.findElement(By.xpath(conpro.getProperty("ObjArea1")));
		element3.clear();
		element3.sendKeys(BranchName);
		WebElement element4 = driver.findElement(By.xpath(conpro.getProperty("ObjZip")));
		element4.clear();
		element4.sendKeys(BranchName);
		driver.findElement(By.xpath(conpro.getProperty("ObjUpdatebutton"))).click();
		String exp_alert=driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String act_alert="Branch Updated";
		if(exp_alert.toLowerCase().contains(act_alert.toLowerCase()))
		{
			Reporter.log(exp_alert,true);
			return true;
		}
		else
		{
			Reporter.log("unable to update branch",true);
			return false;
		}

	}
	//method for logout
	public static boolean adminLogout()
	{
		driver.findElement(By.xpath(conpro.getProperty("Objlogout"))).click();
		if(driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("logout success::",true);
			return true;
		}
		else
		{
			Reporter.log("logout Fail::",true);
			return false;
		}
	}

}



