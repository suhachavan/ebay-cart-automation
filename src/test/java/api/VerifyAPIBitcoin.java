package api;


import org.testng.annotations.Test;

import com.Base.BaseElementTest;
import com.aventstack.extentreports.Status;
import com.utils.Report;
import com.utils.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
 
import static org.testng.Assert.assertTrue;

import java.util.Properties;

import static org.testng.Assert.assertNotNull;
public class VerifyAPIBitcoin extends BaseElementTest {
	protected Properties config;
	 
	@Test
    public void validateBitcoinMarketData() {
		 config = ConfigReader.loadProperties();
		 //Report.test = extent.createTest("Verify Bitcoin Market Data");
		 System.out.println("Connecting to CoinGecko API...");
        Response response = RestAssured
                .given()
                .when()
                .get(config.getProperty("BaseURI"));
        System.out.println("✅ Connection successful. Status Code: " + response.getStatusCode());
      
        
        // Assert HTTP response status
        assertTrue(response.getStatusCode() == 200, "Expected 200 OK response");
        int statusCode = response.getStatusCode();
        if (response.getStatusCode()  == 200) {
            Report.log("✅ Status code is 200", Status.PASS);
        } else {
            Report.log("❌ Invalid status code: " + statusCode, Status.FAIL);
        }
        
      
        
        // Step 2a: Validate presence of USD, GBP, EUR in current_price
        System.out.println("📦 Verifying current_price: USD, GBP, EUR");
        String jsonPathPrefix = "market_data.current_price";
        assertNotNull(response.jsonPath().get(jsonPathPrefix + ".usd"), "USD not found");
        assertNotNull(response.jsonPath().get(jsonPathPrefix + ".gbp"), "GBP not found");
        assertNotNull(response.jsonPath().get(jsonPathPrefix + ".eur"), "EUR not found");
        
     // USD Price
        Number usdPriceNum = response.jsonPath().get("market_data.current_price.usd");
        Double usdPrice = usdPriceNum != null ? usdPriceNum.doubleValue() : null;

        if (usdPrice != null) {
            Report.log("USD Price: " + usdPrice, Status.PASS);
        } else {
            Report.log("USD Price is missing!", Status.FAIL);
        }

        // GBP Price
        Number gbpPriceNum = response.jsonPath().get("market_data.current_price.gbp");
        Double gbpPrice = gbpPriceNum != null ? gbpPriceNum.doubleValue() : null;

        if (gbpPrice != null) {
            Report.log("GBP Price: " + gbpPrice, Status.PASS);
        } else {
            Report.log("GBP Price is missing!", Status.FAIL);
        }
        
        
        double eurPrice = response.jsonPath().getDouble("market_data.current_price.eur");
        

        System.out.println("   ✅ USD Price: " + usdPrice);
        System.out.println("   ✅ GBP Price: " + gbpPrice);
        System.out.println("   ✅ EUR Price: " + eurPrice);
        assertNotNull(usdPrice, "USD price is missing");
        assertNotNull(gbpPrice, "GBP price is missing");
        assertNotNull(eurPrice, "EUR price is missing");

        // Step 2b: Validate market cap and total volume exist for each currency
        System.out.println("📦 Verifying market_cap and total_volume for USD, GBP, EUR");
        String capPath = "market_data.market_cap";
        String volPath = "market_data.total_volume";

        assertNotNull(response.jsonPath().get(capPath + ".usd"), "Market cap USD missing");
        assertNotNull(response.jsonPath().get(capPath + ".gbp"), "Market cap GBP missing");
        assertNotNull(response.jsonPath().get(capPath + ".eur"), "Market cap EUR missing");

        assertNotNull(response.jsonPath().get(volPath + ".usd"), "Volume USD missing");
        assertNotNull(response.jsonPath().get(volPath + ".gbp"), "Volume GBP missing");
        assertNotNull(response.jsonPath().get(volPath + ".eur"), "Volume EUR missing");
        
        double usdCap = response.jsonPath().getDouble("market_data.market_cap.usd");
        double gbpCap = response.jsonPath().getDouble("market_data.market_cap.gbp");
        double eurCap = response.jsonPath().getDouble("market_data.market_cap.eur");

        double usdVol = response.jsonPath().getDouble("market_data.total_volume.usd");
        double gbpVol = response.jsonPath().getDouble("market_data.total_volume.gbp");
        double eurVol = response.jsonPath().getDouble("market_data.total_volume.eur");

        System.out.println("   ✅ Market Cap USD: " + usdCap);
        System.out.println("   ✅ Market Cap GBP: " + gbpCap);
        System.out.println("   ✅ Market Cap EUR: " + eurCap);

        System.out.println("   ✅ Volume USD: " + usdVol);
        System.out.println("   ✅ Volume GBP: " + gbpVol);
        System.out.println("   ✅ Volume EUR: " + eurVol);

        assertNotNull(usdCap, "USD market cap is missing");
        assertNotNull(gbpCap, "GBP market cap is missing");
        assertNotNull(eurCap, "EUR market cap is missing");
        assertNotNull(usdVol, "USD volume is missing");
        assertNotNull(gbpVol, "GBP volume is missing");
        assertNotNull(eurVol, "EUR volume is missing");
        
        System.out.println("📈 Verifying 24h price change percentage...");
        Double priceChange24h = response.jsonPath().getDouble("market_data.price_change_percentage_24h");
        assertNotNull(priceChange24h, "Price change percentage (24h) is missing");
        System.out.println("✅ 24h Price Change Percentage: " + priceChange24h);
        

  
    }
 

}  