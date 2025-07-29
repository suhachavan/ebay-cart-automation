package com.TestClasses;
 
 
import org.testng.annotations.Test;

import com.Base.BaseElementTest;
import com.aventstack.extentreports.Status;
import com.pageobjects.CartPage;
import com.utils.Report;
 
 

 


 
public class CartAdditionTest extends BaseElementTest {

 
	
	@Test(description = "Verify Cart Addition")
	public void SearchBook() throws InterruptedException {
 		Report.test = extent.createTest("Verify Search Operation");
 		CartPage cartpage = new CartPage(driver);
 		cartpage.searchBooks();
 	 
 		cartpage.ClickOntheFirstLink();
 		
 		cartpage.ClickAddtoCart();
 		 
	}
	 
 
    
    
}
 
