package com.pageobjects;

import java.time.Duration;
import java.util.Set;
 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.Base.BaseElementTest;
import com.aventstack.extentreports.Status;
import com.utils.Report;
 

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

public class CartPage extends BaseElementTest {
	
	
//*********** Addeded Locators  ****************************
	
private static By txtboxInput = By.xpath("//*[@id='gh-ac-wrap']//input");
private static By btnsearch = By.id("gh-search-btn");
private static By lnkfirst = By.xpath("((//*[@class='s-item__watchheart-icon']/ancestor::li[1])[1]//following::*[contains(@class,'image')])[1]");
private static By btnAddtoCart = By.xpath("//*[text()='Add to cart']");
private static By cartIconNum = By.xpath("//*[@class='gh-cart__icon']//*[contains(@aria-label,'Your shopping cart contains 1 items')]");
private static By clsoverlay = By.xpath("//*[@aria-label='Close overlay']");
private  WebDriver driver;	
private static final Logger logger = LogManager.getLogger(CartPage.class);
 
 
public CartPage(WebDriver driver) {
    this.driver = driver;
}	
	
//*********** Reusable Methods  ****************************	

public   void searchBooks() {
	

	driver.findElement(txtboxInput).sendKeys("book");
    driver.findElement(btnsearch).click();
   

}

public   void ClickOntheFirstLink () {
	 driver.findElement(lnkfirst).click();
	 
	 logger.info("Clicked on the first link ");
	 
	 
}

public void ClickAddtoCart() throws InterruptedException {
	 
	switchToChildAndClickOnly(btnAddtoCart,"1");
	 
	 
}

public void VerifyCartItemsNumber(String ExpOutcome) throws InterruptedException {
	SoftAssert softassert = new SoftAssert(); 
	
	
	driver.findElement(clsoverlay).click();
	Thread.sleep(5000);
	driver.findElement(cartIconNum).isDisplayed();
	String numbr = driver.findElement(cartIconNum).getText();
	Report.log("Cart Shows as :"+numbr, Status.INFO);
	softassert.assertEquals(numbr, ExpOutcome);
	softassert.assertAll();
	
	 
	 if (numbr.equalsIgnoreCase(ExpOutcome)) {
         Report.log("Expected Items Matched In the Cart", Status.PASS);
     } else {
         Report.log("Expected Items Matched In the Cart", Status.FAIL);
     }
}

public void switchToChildAndClickOnly(By element, String ExpOutCome) throws InterruptedException {
	
	String parentWindowHandle = driver.getWindowHandle();
    Set<String> allWindows = driver.getWindowHandles();

    for (String window : allWindows) {
        if (!window.equals(parentWindowHandle)) {
            driver.switchTo().window(window);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            logger.info("Clicked on the child window element ");
             
            VerifyCartItemsNumber(ExpOutCome);
            break;
        }
    }

    driver.switchTo().window(parentWindowHandle);
    
    logger.info("Switched back to parent window");
        
}
	
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
