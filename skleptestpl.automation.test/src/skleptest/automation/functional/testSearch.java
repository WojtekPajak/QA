package skleptest.automation.functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testSearch {


	public WebDriver wd;
	public String mainPageUrl;
	public String searchPhrase;
	WebElement actualSearchResult;

	
	@BeforeTest
		public void testSearchSetUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Drivers\\chromedriver.exe");
			wd = new ChromeDriver();
			wd.manage().window().maximize();
			mainPageUrl = "http://skleptest.pl/";
		
		}
		
	@Test // Class testing if system find search result for used word - POSITIVE RESULT
		public void searchPositive() {
			searchPhrase = "top";
			
			wd.get(mainPageUrl);
			wd.findElement(By.className("search-field-top-bar")).sendKeys(searchPhrase);
			wd.findElement(By.className("search-top-bar-submit")).click();
			actualSearchResult = wd.findElement(By.className("page-title"));
			Assert.assertEquals("SEARCH RESULTS FOR: TOP", actualSearchResult.getText());
		}

	@Test(priority = 1) // Class testing if system find search result for used word - NEGATIVE RESULT
		public void searchNegative() {
			searchPhrase = "notexistingproduct";
			
			wd.get(mainPageUrl);
			wd.findElement(By.className("search-field-top-bar")).sendKeys(searchPhrase);
			wd.findElement(By.className("search-top-bar-submit")).click();
			actualSearchResult = wd.findElement(By.className("page-title"));
			Assert.assertNotEquals("SEARCH RESULTS FOR: notexistingproduct", actualSearchResult.getText());
		}
		

	@Test(priority = 2) // Class testing "next search" button operation 
		public void nextSearchButtonTest() {
			searchPhrase = "notexistingproduct";
			
			wd.get(mainPageUrl);
			wd.findElement(By.className("search-field-top-bar")).sendKeys(searchPhrase);
			wd.findElement(By.className("search-top-bar-submit")).click();
			wd.findElement(By.className("search-field")).clear();
			wd.findElement(By.className("search-field")).sendKeys("top");
			wd.findElement(By.className("search-submit")).click();
			actualSearchResult = wd.findElement(By.className("page-title"));
			Assert.assertEquals("SEARCH RESULTS FOR: TOP", actualSearchResult.getText());
		}
	
	@AfterTest
		public void testSearchFinish() {
			wd.quit();
		}
		
}
