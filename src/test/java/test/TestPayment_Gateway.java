package test;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import demopackage.Payment_Gateway;
import frameWork.Log;

public class TestPayment_Gateway extends Payment_Gateway {
	
	@BeforeClass
	public void testBeforeClass() throws IOException {
		before_Class(); 
	}
	
@Test
	
	public void tab_verification() {
		testCaseID = "Guru_Demowebsite_Payment tab verification";
		Log.startTestCase(testCaseID, "Guru_Insurance_Payment tab verification");
		try {
			//initialize the element
			message = " Initialize elements";
			initializeElement();
			Log.getReport(message);
			
			//list_Ele();
			//purchase();
			//balance();
			
			message="Login to Insurance page";
			//payTab_Validation("Cart","Mother Elephant","Elephant","Cart Tab");
			payTab_Validation("Generate Card Number","New Card","New Card","Generating card Number");
	        //payTab_Validation("Check Credit Card Limit","Check Credit","Check Credit","Check Credit Card Limit");
			//purchase();
			//balance_validation();
			//balance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.endTestCase("Guru_Insurance_Payment tab verification");		
	}

	@AfterClass
	public void testAfterClass() throws InterruptedException {
		closeDriver();
	}
			
}


