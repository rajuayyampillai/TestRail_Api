package selenium_automation_code;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.gurock.testrail.APIException;

import testrail_Communication_Api.Program;

public class Google_search {
	
	static Program api;
	public static void main(String args[]) throws InterruptedException, MalformedURLException, IOException, APIException{
		
		
		String CURL="";
		api=new Program();
		api.intializeClient(CURL);
		api.setUser("", "");
	    long runnerid= api.add_run(2, 2, "Creating runner "," Creating runner using selenium api", 1, true);
	   
		
	   WebDriver driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	    
	    String Pagetitle= driver.getTitle();
        if(Pagetitle.equals("Google")){
        	api.add_result_for_Case(runnerid,4,"pass","Page loaded suceessfully");
            }
        else{
        	api.add_result_for_Case(runnerid,4,"fail","Page didn't load");
            }
		Thread.sleep(2000);   
		
		//tcid="2";
		driver.findElement(By.id("lst-ib")).click();
		driver.findElement(By.id("lst-ib")).sendKeys("TestRail");
		Thread.sleep(2000);
		  Pagetitle= driver.getTitle();
		  System.out.print(Pagetitle);
		if(Pagetitle.equals("testrail - Google Search")){
        	
        	api.add_result_for_Case(runnerid,5,"pass","Page loaded suceessfully");
        }
        else{
        	
    	    api.add_result_for_Case(runnerid,5,"fail","Page didn't load");
        	
           }	
		
	}
	
	
	

}
