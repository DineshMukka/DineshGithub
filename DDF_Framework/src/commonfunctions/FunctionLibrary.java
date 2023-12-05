package commonfunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.Apputil;

public class FunctionLibrary extends Apputil {
	public static boolean verify_login(String user,String pass)
	{
		driver.get(conpro.getProperty("Url"));
		driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("ObjPass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		String expected="dashboard";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("login sucess::"+expected+"  "+actual,true);
			return true;
		}
		Reporter.log("login failed::"+expected+"  "+actual,true);
		return false;
		}
	}
	


