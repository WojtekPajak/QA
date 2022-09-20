package skleptest.automation.functional;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testNewsletter {

	public WebDriver wd;
	public String mainPageUrl;
	public String newsName;
	public String newsMail;

	
	@BeforeTest
		public void testNewsletterSetUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
			wd = new ChromeDriver();
			mainPageUrl = "http://skleptest.pl/";
			wd.manage().window().maximize();
			
		}
	
	@Test //Class testing if system accepts subscribe inquiry, when no informations are entered into fields - NEGATIVE RESULT
		public void subscribeNegative() {
			newsName = "";
			newsMail = "";
			
			wd.get(mainPageUrl);
			wd.findElement(By.id("es_txt_name")).sendKeys(newsName);
			wd.findElement(By.id("es_txt_email")).sendKeys(newsMail);
			wd.findElement(By.id("es_txt_button")).click();			
			String resultAlertText = wd.switchTo().alert().getText();
			String expectedAlertText = "Please enter email address";
			Assert.assertEquals(expectedAlertText, resultAlertText);
	}
	
	@AfterTest
		public void textNewsletterFinish() {
			wd.quit();
		}
}
